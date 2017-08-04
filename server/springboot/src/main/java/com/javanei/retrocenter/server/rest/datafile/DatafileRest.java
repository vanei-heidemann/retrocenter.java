package com.javanei.retrocenter.server.rest.datafile;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.datafile.parser.DatafileParser;
import com.javanei.retrocenter.datafile.service.DatafileDTO;
import com.javanei.retrocenter.datafile.service.DatafileService;
import com.javanei.retrocenter.datafile.service.RetrocenterDatafileService;
import com.javanei.retrocenter.server.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/datafiles")
@Api(tags = {"Datafiles service"}, produces = "application/json")
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
    public List<DatafileDTO> find() {
        return retrocenterDatafileService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> findById(@PathVariable Long id) {
        DatafileDTO vo = retrocenterDatafileService.findByIDFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo.toFile());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Parse and save datafile uploaded")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "File is empty")
    })
    public ResponseEntity<Datafile> upload(@RequestParam("file") MultipartFile uploadfile) throws Exception {
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
        return new ResponseEntity(r, HttpStatus.CREATED);
    }
}
