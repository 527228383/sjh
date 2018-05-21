package com.sjh.export;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "导出excel模块")
@RestController
@RequestMapping(value = "/export")
public class ExportExcel {
	
	@ApiOperation(value = "导出excel", notes = "导出excel")
	@PostMapping("/ExportExcel1")
	public void ExportExcel1() {
		
	}
	
}
