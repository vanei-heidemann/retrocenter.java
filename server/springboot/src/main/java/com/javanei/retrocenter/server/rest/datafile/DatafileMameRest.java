package com.javanei.retrocenter.server.rest.datafile;

import com.javanei.retrocenter.mame.service.MameDTO;
import com.javanei.retrocenter.mame.service.MameService;
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
@Api(tags = {"Datafiles service - MAME", "MAME"})
public class DatafileMameRest {
    @Autowired
    private MameService mameService;

    @RequestMapping(value = "/mame", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list MAME datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public List<MameDTO> findMame() {
        return mameService.findAll();
    }

    @RequestMapping(value = "/mame/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return MAME data file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<MameDTO> findById(@PathVariable Long id) {
        MameDTO vo = mameService.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/mame/{id}/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the MAME xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> download(@PathVariable Long id) {
        MameDTO vo = mameService.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
