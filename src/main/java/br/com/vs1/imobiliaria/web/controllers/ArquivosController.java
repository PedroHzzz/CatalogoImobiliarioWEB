package br.com.vs1.imobiliaria.web.controllers;

import br.com.vs1.imobiliaria.web.services.WebArquivoUploadService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@RequestMapping("/arquivos")
@Controller
public class ArquivosController {

    private final WebArquivoUploadService webArquivoUploadService;

    public ArquivosController(WebArquivoUploadService webArquivoUploadService) {
        this.webArquivoUploadService = webArquivoUploadService;
    }

    @GetMapping
    public void arquivo(HttpServletResponse response, @RequestParam("nome") String nome) throws IOException {
        byte[] arquivo = webArquivoUploadService.getFileFromDisk(nome);
        response.setContentType("image/jpeg");
        response.getOutputStream().write(arquivo);


    }

    @GetMapping("/download")
    public void baixar(HttpServletResponse response, @RequestParam("nome") String nome) throws IOException {
        webArquivoUploadService.downloadFileFromDisk(nome, response);
    }

}
