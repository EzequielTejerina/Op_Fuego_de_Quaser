package challenge.controller;

import challenge.exception.ServiceException;
import challenge.services.CommunicationService;
import challenge.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommunicationController extends BaseController{

    @Autowired
    private CommunicationService communicationService;

    @PostMapping(value = "/topsecret/", consumes = JSON_UTF8, produces = JSON_UTF8)
    public ResponseEntity<Object> topSecret(@RequestBody String requestBody) throws ServiceException {
        try{
            this.logger.info("Llamada al servicio /topsecret/ con el request body : {}", requestBody);
            return new ResponseEntity<>(this.communicationService.topSecret(requestBody), HttpStatus.OK);
        }catch(ServiceException ex){
            throw ex;
        }catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @RequestMapping(value = "/topsecret_split/{satellite_name}", consumes = JSON_UTF8, produces = JSON_UTF8)
    public ResponseEntity<Object> topSecretSplit(@PathVariable String satelliteName, @RequestBody String requestBody) throws ServiceException {
        try{
            this.logger.info("Llamada al servicio /topsecret_split/ con el request body : {}", requestBody);
            return new ResponseEntity<>(this.communicationService.topSecretSplit(satelliteName, requestBody), HttpStatus.OK);
        } catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
