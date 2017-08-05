package com.javanei.retrocenter.server.rest.datafile;

import com.javanei.retrocenter.logiqx.service.LogiqxDatafileDTO;
import com.javanei.retrocenter.logiqx.service.LogiqxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/datafiles")
@Api(tags = {"Datafiles service - Logiqx", "Logiqx"})
public class DatafileLogiqxRest {
    @Autowired
    private LogiqxService logiqxService;

    @RequestMapping(value = "/logiqx", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list logiqx datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public List<LogiqxDatafileDTO> findLogiq() {
        return logiqxService.findAll();
    }

    @RequestMapping(value = "/logiqx/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return Logiqx data file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<LogiqxDatafileDTO> findByLogiqxId(@PathVariable Long id) {
        LogiqxDatafileDTO vo = logiqxService.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/logiqx/{id}/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the Logiqx xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> downloadLogiqxById(@PathVariable Long id) {
        LogiqxDatafileDTO vo = logiqxService.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
