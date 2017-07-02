package com.javanei.retrocenter.server;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.datafile.parser.DatafileParser;
import com.javanei.retrocenter.datafile.service.DatafileService;
import com.javanei.retrocenter.datafile.service.RetrocenterDatafileService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/datafiles")
public class DatafileRest {
    private static final Logger LOG = LoggerFactory.getLogger(DatafileRest.class);

    @Autowired
    public DatafileService service;
    @Autowired
    RetrocenterDatafileService retrocenterDatafileService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Datafile> find() {
        return retrocenterDatafileService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Datafile> upload(@RequestParam("file") MultipartFile uploadfile) throws Exception {
        if (uploadfile.isEmpty()) {
            LOG.info("File is empty");
            return null;
        }
        LOG.info("File uploaded: " + uploadfile.getOriginalFilename() + ", size=" + uploadfile.getSize());

        Parser parser = new DatafileParser();
        LOG.info("Parser: " + parser.getClass());
        DatafileObject datafile = parser.parse(uploadfile.getInputStream());
        LOG.info("Datafile: " + datafile.getClass());
        Datafile r = service.create(datafile);
        LOG.info("Result: " + r.getClass());

        return new ResponseEntity(r, HttpStatus.OK);
    }
}
