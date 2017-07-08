package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.clrmamepro.service.CMProService;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import com.javanei.retrocenter.hyperlist.HyperListMenu;
import com.javanei.retrocenter.hyperlist.service.HyperListService;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.service.LogiqxService;
import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.service.MameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatafileService {
    private static final Logger LOG = LoggerFactory.getLogger(DatafileService.class);

    @Autowired
    private MameService mameService;
    @Autowired
    private CMProService cmProService;
    @Autowired
    private LogiqxService logiqxService;
    @Autowired
    private HyperListService hyperListService;
    @Autowired
    private RetrocenterDatafileService retrocenterDatafileService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Datafile create(DatafileObject datafileObject) {
        LOG.info("create(" + datafileObject.getClass() + ")");
        Datafile datafile;
        if (datafileObject instanceof Mame) {
            datafile = mameService.create((Mame) datafileObject).toDatafile();
        } else if (datafileObject instanceof CMProDatafile) {
            datafile = cmProService.create((CMProDatafile) datafileObject).toDatafile();
        } else if (datafileObject instanceof LogiqxDatafile) {
            datafile = logiqxService.create((LogiqxDatafile) datafileObject).toDatafile();
        } else if (datafileObject instanceof HyperListMenu) {
            datafile = hyperListService.create((HyperListMenu) datafileObject).toDatafile();
        } else {
            datafile = (Datafile) datafileObject;
        }
        LOG.info("datafile=" + datafile.getClass());
        return retrocenterDatafileService.create(datafile);
    }
}
