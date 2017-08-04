package com.javanei.retrocenter.server.rest.gamedb.launchbox;

import com.javanei.retrocenter.gamedb.launchbox.common.LBoxDatafile;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxGame;
import com.javanei.retrocenter.gamedb.launchbox.parser.LaunchBoxParser;
import com.javanei.retrocenter.gamedb.launchbox.service.LBoxDatafileDTO;
import com.javanei.retrocenter.gamedb.launchbox.service.LBoxGenreDTO;
import com.javanei.retrocenter.gamedb.launchbox.service.LBoxPlatformDTO;
import com.javanei.retrocenter.gamedb.launchbox.service.LBoxRegionDTO;
import com.javanei.retrocenter.gamedb.launchbox.service.LBoxService;
import com.javanei.retrocenter.server.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/gamedb/launchbox")
@Api(tags = {"LaunchBox service"}, produces = "application/json")
public class LaunchBoxRest {
    private static final Logger LOG = LoggerFactory.getLogger(LaunchBoxRest.class);

    @Autowired
    public LBoxService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxDatafileDTO>> findDatafiles() {
        return ResponseEntity.ok(service.findDatafiles());
    }

    @RequestMapping(value = "/{version:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Parse and save LaunchBox Datafiles")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "Datafile is empty")
    })
    public ResponseEntity<LBoxDatafileDTO> upload(
            @PathVariable(value = "version", required = true) String version,
            @RequestParam(value = "metadataXML", required = true) MultipartFile metadataXML,
            @RequestParam(value = "filesXML", required = true) MultipartFile filesXML) throws Exception {
        if (metadataXML.isEmpty()) {
            LOG.info("metadataXML is empty");
            return new ResponseEntity(new ErrorResponse("File is empty"), HttpStatus.BAD_REQUEST);
        }
        LOG.info("MetadataXML.xml uploaded: " + metadataXML.getOriginalFilename() + ", size=" + metadataXML.getSize());
        LOG.info("Files.xml uploaded: " + filesXML.getOriginalFilename() + ", size=" + filesXML.getSize());

        LaunchBoxParser parser = new LaunchBoxParser();
        LBoxDatafile datafile = parser.parse(metadataXML.getInputStream(), filesXML.getInputStream());
        datafile.setVersion(version);
        LOG.info("Datafile: " + datafile);
        LBoxDatafileDTO result = service.save(datafile);
        LOG.info("Result: " + result);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{version:.+}/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List games by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxGame>> findGamesByVersion(
            @PathVariable(value = "version", required = true) String version,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        Map<String, Object> params = new HashMap<>();
        if (name != null && !name.trim().isEmpty()) {
            params.put("name", name.trim());
        }
        return ResponseEntity.ok(service.findGamesByVersion(version, params,
                new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "game.name"))));
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List games")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxGame>> findGames(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        Map<String, Object> params = new HashMap<>();
        if (name != null && !name.trim().isEmpty()) {
            params.put("name", name.trim());
        }
        return ResponseEntity.ok(service.findGames(params,
                new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"))));
    }

    @RequestMapping(value = "/{version:.+}/platforms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List platforms by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxPlatformDTO>> findPlatformsByVersion(@PathVariable(value = "version", required = true) String version) {
        return ResponseEntity.ok(service.findPlatformsByVersion(version));
    }

    @RequestMapping(value = "/platforms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List platforms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxPlatformDTO>> findPlatforms() {
        return ResponseEntity.ok(service.findPlatforms());
    }

    @RequestMapping(value = "/{version:.+}/regions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List regions by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxRegionDTO>> findRegionsByVersion(@PathVariable(value = "version", required = true) String version) {
        return ResponseEntity.ok(service.findRegionsByVersion(version));
    }

    @RequestMapping(value = "/regions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List regions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxRegionDTO>> findRegions() {
        return ResponseEntity.ok(service.findRegions());
    }

    @RequestMapping(value = "/{version:.+}/genres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List genres by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxGenreDTO>> findGenresByVersion(@PathVariable(value = "version", required = true) String version) {
        return ResponseEntity.ok(service.findGenresByVersion(version));
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List genres")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public ResponseEntity<List<LBoxGenreDTO>> findGenres() {
        return ResponseEntity.ok(service.findGenres());
    }
}
