package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.transaction.CreateTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowAllTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowTransactionDTO;
import com.springBoot1.SB2.dto.transaction.UpdateTransactionDTO;
import com.springBoot1.SB2.util.ResourceLoaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("${API.URL_PREFIX}transactions")
public class TransactionController extends CURDTrashableController<Long, CreateTransactionDTO, ShowAllTransactionDTO, ShowTransactionDTO, UpdateTransactionDTO> {
    @Autowired
    private ResourceLoaderUtil loaderUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @GetMapping("/load-file")
    public void loadFile(@RequestParam(value = "file") String filename) throws IOException {
        loaderUtil.loadAndWriteFile(filename,request,response);
    }
}
