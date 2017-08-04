package com.javanei.retrocenter.server.rest.artifact;

import com.javanei.retrocenter.common.Identified;
import com.javanei.retrocenter.platform.common.Artifact;
import com.javanei.retrocenter.platform.service.ArtifactService;
import com.javanei.retrocenter.server.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping(value = "/api/artifacts")
@Api(tags = {"Artifacts service"}, produces = "application/json")
public class ArtifactRest {
    @Autowired
    private ArtifactService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find a artifact by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Artifact Not Found")
    })
    public ResponseEntity<ArtifactVO> findByID(@PathVariable("id") Long id) throws Exception {
        Identified<Artifact> p = service.findArtifactById(id);
        if (p != null) {
            ArtifactVO vo = new ArtifactVO(p.getId(), p.get().getCode(), p.get().getName());
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list of artifacts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<ArtifactVO>> find(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize)
            throws Exception {
        List<Identified<Artifact>> values = service.findArtifacts(null, code, name
                , new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name")));
        List<ArtifactVO> result = new ArrayList<>();
        for (Identified<Artifact> p : values) {
            ArtifactVO vo = new ArtifactVO(p.getId(), p.get().getCode(), p.get().getName());
            result.add(vo);
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new artifact")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "Artifact already exists")
    })
    public ResponseEntity<ArtifactVO> create(@RequestBody Artifact artifact) throws Exception {
        if (service.findArtifactByCode(artifact.getCode()) != null) {
            return new ResponseEntity(new ErrorResponse("Artifact already exists"), HttpStatus.BAD_REQUEST);
        }
        Identified<Artifact> p = service.createArtifact(artifact);
        ArtifactVO vo = new ArtifactVO(p.getId(), p.get().getCode(), p.get().getName());
        return new ResponseEntity(vo, HttpStatus.CREATED);
    }
}
