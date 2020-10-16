package challenge.controller;

import challenge.exception.ServiceException;
import challenge.services.CommunicationService;
import challenge.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
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
    @PostMapping(value = "/topsecret_split/{satellite_name}", consumes = JSON_UTF8, produces = JSON_UTF8)
    public ResponseEntity<Object> topSecretSplitPost(@PathVariable(name = "satellite_name") String satelliteName, @RequestBody String requestBody) throws ServiceException {
        try{
            this.logger.info("Llamada al servicio post /topsecret_split/ con el request body : {}", requestBody);
            return new ResponseEntity<>(this.communicationService.saveTopSecretSplit(satelliteName, requestBody), HttpStatus.OK);
        } catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @GetMapping(value = "/topsecret_split/", consumes = JSON_UTF8, produces = JSON_UTF8)
    public ResponseEntity<Object> topSecretSplitGet() throws ServiceException {
        try{
            this.logger.info("Llamada al servicio get /topsecret_split/");
            return new ResponseEntity<>(this.communicationService.getTopSecretSplit(), HttpStatus.OK);
        } catch(Exception e){
            this.logger.error(ExceptionUtil.getStackTra(e));
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
