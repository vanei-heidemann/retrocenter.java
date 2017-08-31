package com.javanei.retrocenter.platform.service;

import com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import com.javanei.retrocenter.common.PaginatedResult;
import com.javanei.retrocenter.common.PlatformNotFoundException;
import com.javanei.retrocenter.common.RetrocenterException;
import com.javanei.retrocenter.common.util.FileUtil;
import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ZipUtil;
import com.javanei.retrocenter.platform.entity.PlatformArtifactFileEntity;
import com.javanei.retrocenter.platform.entity.PlatformArtifactFileImportHistoryEntity;
import com.javanei.retrocenter.platform.entity.PlatformArtifactFileInfoEntity;
import com.javanei.retrocenter.platform.entity.PlatformEntity;
import com.javanei.retrocenter.platform.persistence.PlatformArtifactFileDAO;
import com.javanei.retrocenter.platform.persistence.PlatformArtifactFileImportHistoryDAO;
import com.javanei.retrocenter.platform.persistence.PlatformArtifactFileInfoDAO;
import com.javanei.retrocenter.platform.persistence.PlatformDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PlatformArtifactFileService {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformArtifactFileService.class);

    @Autowired
    private PlatformDAO platformDAO;

    @Autowired
    private PlatformArtifactFileDAO fileDAO;
    @Autowired
    private PlatformArtifactFileInfoDAO infoDAO;
    @Autowired
    private PlatformArtifactFileImportHistoryDAO historyDAO;

    private PlatformArtifactFileSavedDTO importFile(PlatformEntity platform, String repositoryBaseDir, String importInfo,
                                                    String originalArtifactFileName, ArtifactFileTypeEnum type,
                                                    byte[] fileContent) throws NoSuchAlgorithmException, IOException {
        LOG.info("  importFile(originalArtifactFileName=" + originalArtifactFileName + ")");
        PlatformArtifactFileSavedDTO result = new PlatformArtifactFileSavedDTO();
        result.setPlatformId(platform.getId());
        result.setPlatformName(platform.getName());
        result.setFileName(originalArtifactFileName);
        result.setType(type.name());

        PlatformArtifactFileEntity entity = new PlatformArtifactFileEntity();
        entity.setType(type.name());
        entity.setCrc(StringUtil.toStringCRC(FileUtil.getCRC(fileContent)));
        entity.setMd5(StringUtil.toHexString(FileUtil.getMD5(fileContent)));
        entity.setSha1(StringUtil.toHexString(FileUtil.getSHA1(fileContent)));
        entity.setName(entity.getSha1());
        entity.setSize(new Long(fileContent.length));
        entity.setPlatform(platform);

        List<PlatformArtifactFileEntity> l = fileDAO.findByPlatform_idAndSha1(platform.getId(),
                entity.getSha1());
        for (PlatformArtifactFileEntity e : l) {
            if (entity.getCrc().equals(e.getCrc())
                    && entity.getMd5().equals(e.getMd5())
                    && entity.getSize().longValue() == e.getSize().longValue()) {
                LOG.info("  Já existe (" + e.getId() + ")");
                entity = e;
                break;
            }
        }

        if (entity.getId() == null) {
            // Ainda não existe
            PlatformArtifactFileEntity e = fileDAO.findByPlatform_idAndName(platform.getId(),
                    entity.getName());
            if (e != null) {
                // Já existe, concatena o CRC no nome
                e = fileDAO.findByPlatform_idAndName(platform.getId(),
                        entity.getName() + "-" + entity.getCrc());
                if (e != null) {
                    // Também já existe, tenta concatenar o size
                    e = fileDAO.findByPlatform_idAndName(platform.getId(),
                            entity.getName() + "-" + entity.getSize());
                    if (e != null) {
                        // Também já existe! Usa um número qualquer
                        entity.setName(entity.getName() + "-" + System.currentTimeMillis());
                    } else {
                        entity.setName(entity.getName() + "-" + entity.getSize());
                    }
                } else {
                    entity.setName(entity.getName() + "-" + entity.getCrc());
                }
            }
        }

        File file = new File(repositoryBaseDir);
        file = new File(file, platform.getStorageFolder());
        //file = new File(file, entity.getType());
        file = new File(file, entity.getName() + ".zip");
        LOG.info("  Salvando para arquivo: " + file.getAbsolutePath());
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            ZipUtil.addFileToZip(file, entity.getName(), originalArtifactFileName, fileContent);
        }

        entity = fileDAO.save(entity);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PlatformArtifactFileImportHistoryEntity history = new PlatformArtifactFileImportHistoryEntity();
        history.setPlatform(platform);
        if (importInfo == null || importInfo.trim().isEmpty()) {
            history.setDescription(platform.getName() + ": " + df.format(new Date()));
        } else {
            history.setDescription(importInfo);
        }
        PlatformArtifactFileImportHistoryEntity eHistory = historyDAO.findByPlatform_idAndDescription(platform.getId(),
                history.getDescription());
        if (eHistory != null) {
            history = eHistory;
        }
        history.setLastDate(df.format(new Date()));
        history = historyDAO.save(history);
        result.setImportInfo(history.getDescription());

        PlatformArtifactFileInfoEntity info = infoDAO.findByPlatformArtifactFile_idAndInfo(entity.getId(),
                originalArtifactFileName);
        if (info == null) {
            info = new PlatformArtifactFileInfoEntity();
            info.setInfo(originalArtifactFileName.contains("/")
                    ? originalArtifactFileName.substring(originalArtifactFileName.lastIndexOf("/") + 1)
                    : originalArtifactFileName);
            info.setPlatformArtifactFile(entity);
            info = infoDAO.save(info);
            result.setFileName(info.getInfo());
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PlatformArtifactFileSavedDTO> importFile(Long platformId, String repositoryBaseDir, String importInfo,
                                                         String originalArtifactFileName, ArtifactFileTypeEnum type,
                                                         byte[] fileContent)
            throws NoSuchAlgorithmException, IOException, RetrocenterException {
        LOG.info("importFile(platformId=" + platformId + ", repositoryBaseDir=" + repositoryBaseDir
                + ", importInfo=" + importInfo + ", originalArtifactFileName=" + originalArtifactFileName
                + ", type=" + type + ", fileContent=" + fileContent.length + ")"
        );
        List<PlatformArtifactFileSavedDTO> result = new LinkedList<>();

        PlatformEntity platform = platformDAO.findOne(platformId);
        if (platform == null) {
            throw new PlatformNotFoundException(platformId);
        }
        LOG.info("Platform: " + platform);
        if (ZipUtil.isZip(fileContent)) {
            LOG.info("  Is zip file");
            Map<String, byte[]> files = ZipUtil.extractToByteArray(fileContent);
            for (String name : files.keySet()) {
                result.add(importFile(platform, repositoryBaseDir, importInfo, name, type, files.get(name)));
            }
        } else {
            result.add(importFile(platform, repositoryBaseDir, importInfo, originalArtifactFileName, type, fileContent));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PaginatedResult<PlatformArtifactFileDTO> findFilesByPlatform(Long platformId,
                                                                        int page,
                                                                        int pageSize,
                                                                        boolean showInfo) throws PlatformNotFoundException {
        PlatformEntity platform = platformDAO.findOne(platformId);
        if (platform == null) {
            throw new PlatformNotFoundException(platformId);
        }
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<PlatformArtifactFileEntity> l = fileDAO.findByPlatform_id(platformId, paging);
        PaginatedResult<PlatformArtifactFileDTO> r = new PaginatedResult<>(l.hasNext());
        for (PlatformArtifactFileEntity e : l) {
            PlatformArtifactFileDTO vo = new PlatformArtifactFileDTO(e.getId(), e.getName(), e.getType(), e.getSize(),
                    e.getCrc(), e.getMd5(), e.getSha1());
            r.add(vo);
            if (showInfo) {
                List<PlatformArtifactFileInfoEntity> li = infoDAO.findByPlatformArtifactFile_id(e.getId());
                for (PlatformArtifactFileInfoEntity ie : li) {
                    vo.addInfo(ie.getInfo());
                }
            }
        }
        return r;
    }
}
