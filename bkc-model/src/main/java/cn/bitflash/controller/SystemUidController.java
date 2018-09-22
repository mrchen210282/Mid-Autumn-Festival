package cn.bitflash.controller;

import cn.bitflash.service.SystemUidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class SystemUidController {

    @Autowired
    private SystemUidService SystemUidService;


}
