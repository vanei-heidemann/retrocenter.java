package com.javanei.retrocenter.server.rest.datafile;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.datafile.parser.DatafileParser;
import com.javanei.retrocenter.datafile.service.DatafileService;
import com.javanei.retrocenter.datafile.service.RetrocenterDatafileService;
import com.javanei.retrocenter.server.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Datafiles service", description = "Management of datafiles")
public class DatafileRest {
    private static final Logger LOG = LoggerFactory.getLogger(DatafileRest.class);

    @Autowired
    public DatafileService service;
    @Autowired
    RetrocenterDatafileService retrocenterDatafileService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list of datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public List<Datafile> find() {
        return retrocenterDatafileService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Parse and save datafiled uploaded")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "File is empty"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Datafile> upload(@RequestParam("file") MultipartFile uploadfile) {
        try {
            if (uploadfile.isEmpty()) {
                LOG.info("File is empty");
                return new ResponseEntity(new ErrorResponse("File is empty"), HttpStatus.BAD_REQUEST);
            }
            LOG.info("File uploaded: " + uploadfile.getOriginalFilename() + ", size=" + uploadfile.getSize());

            Parser parser = new DatafileParser();
            LOG.info("Parser: " + parser.getClass());
            DatafileObject datafile = parser.parse(uploadfile.getInputStream());
            LOG.info("Datafile: " + datafile.getClass());
            Datafile r = service.create(datafile);
            LOG.info("Result: " + r.getClass());

            return new ResponseEntity(r, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
