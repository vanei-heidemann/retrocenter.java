package com.javanei.retrocenter.server.rest.platform;

import com.javanei.retrocenter.common.Identified;
import com.javanei.retrocenter.platform.common.Platform;
import com.javanei.retrocenter.platform.service.PlatformService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/platforms")
@Api(tags = {"Platforms service"}, produces = "application/json")
public class PlatformRest {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformRest.class);
    @Autowired
    public PlatformService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find a platform by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Platform Not Found")
    })
    public ResponseEntity<PlatformVO> findByID(@PathVariable("id") Long id) throws Exception {
        Identified<Platform> p = service.findPlatformByID(id);
        if (p != null) {
            PlatformVO vo = new PlatformVO();
            vo.setId(p.getId());
            vo.setName(p.get().getName());
            vo.setShortName(p.get().getShortName());
            vo.setAlternateNames(p.get().getAlternateNames());
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list of platforms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<PlatformVO>> find(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "alternateName", required = false) String alternateName)
            throws Exception {
        List<Identified<Platform>> platforms = service.findPlatform(name, alternateName);
        List<PlatformVO> result = new ArrayList<>();
        for (Identified<Platform> p : platforms) {
            PlatformVO vo = new PlatformVO();
            vo.setId(p.getId());
            vo.setName(p.get().getName());
            vo.setShortName(p.get().getShortName());
            vo.setAlternateNames(p.get().getAlternateNames());
            result.add(vo);
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new platform")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "Platform already exists")
    })
    public ResponseEntity<PlatformVO> create(@RequestBody Platform platform) throws Exception {
        if (service.findPlatformByName(platform.getName()) != null) {
            return new ResponseEntity(new ErrorResponse("Platform already exists"), HttpStatus.BAD_REQUEST);
        }
        Identified<Platform> p = service.createPlatform(platform);
        PlatformVO vo = new PlatformVO();
        vo.setId(p.getId());
        vo.setName(p.get().getName());
        vo.setShortName(p.get().getShortName());
        vo.setAlternateNames(p.get().getAlternateNames());
        return new ResponseEntity(vo, HttpStatus.CREATED);
    }

    private class PlatformVO extends Platform {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
