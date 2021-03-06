package challenge.controller;

import challenge.exception.ServiceException;
import challenge.services.CommunicationService;
import challenge.utils.ExceptionUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "CommunicationController")
public class CommunicationController extends BaseController{

    @Autowired
    private CommunicationService communicationService;

    @PostMapping(value = "/topsecret/", consumes = JSON_UTF8, produces = JSON_UTF8)
    @ApiOperation(
            value = "TopSecret",
            notes = "Retorna la ubicación de la nave y el mensaje que emite"
    )
    public ResponseEntity<Object> topSecret(@RequestBody String requestBody) throws ServiceException {
        try{
            this.logger.info("Llamada al servicio /topsecret/ con el request body : {}", requestBody);
            return new ResponseEntity<>(this.communicationService.topSecret(requestBody), HttpStatus.OK);
        }catch(ServiceException ex){
            return ResponseEntity.status(ex.getCode()).body(ex.toString());
        }catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @PostMapping(value = "/topsecret_split/{satellite_name}", consumes = JSON_UTF8, produces = JSON_UTF8)
    @ApiOperation(
            value = "TopSecretSplit",
            notes = "Recibe la distancia a los satelite y el mensaje"
    )
    public ResponseEntity<Object> topSecretSplitPost(@PathVariable(name = "satellite_name") String satelliteName, @RequestBody String requestBody) throws ServiceException {
        try{
            this.logger.info("Llamada al servicio post /topsecret_split/ con el request body : {}", requestBody);
            return new ResponseEntity<>(this.communicationService.saveTopSecretSplit(satelliteName, requestBody), HttpStatus.OK);
        } catch(ServiceException ex){
            return ResponseEntity.status(ex.getCode()).body(ex.toString());
        }catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @GetMapping(value = "/topsecret_split/", produces = JSON_UTF8)
    @ApiOperation(
            value = "TopSecretSplit",
            notes = "Retorna la ubicación de la nave y el mensaje que emite"
    )
    public ResponseEntity<Object> topSecretSplitGet() throws ServiceException {
        try{
            this.logger.info("Llamada al servicio get /topsecret_split/");
            return new ResponseEntity<>(this.communicationService.getTopSecretSplit(), HttpStatus.OK);
        }catch(ServiceException ex){
            return ResponseEntity.status(ex.getCode()).body(ex.toString());
        } catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
