package co.com.example.demo.controllers;


import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.io.OutputStream;
import java.util.List;

@RestController()
public class PersonController {

/*    @GetMapping("/download2")
    public Mono<Void> export(ServerHttpResponse response, String name) throws IOException {
        //spring webflux文件下载零拷贝（Zero-copy）
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        //设置文件下载响应头
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=test-"+ DateUtil.format(DateUtil.date(),"yyyy-MM-dd-hhMM-sss") +".xlsx");
        response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //获取数据
            try {


                //将临时文件输出
                fout = new BufferedOutputStream(new FileOutputStream(targetFile));
                //写入工作区
                wb.write(fout);
                //这里将数据写入ServerHttpResponse
                return zeroCopyResponse.write(targetFile, 0, targetFile.length());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }*/


    @Autowired
    TitleOpenFeignClient openFeignClient;


/*    @GetMapping("/download2")
    public Response.Body download2(String name, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=remote-"+ DateUtil.format(DateUtil.date(),"yyyy-MM-dd-hhMM-sss") +".xlsx");
        IOUtils.copy(openFeignClient.downloadExcel(name).body().asInputStream(),response.getOutputStream());
*//*        serverResponse.getHeaders().set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                "filename=remote-"+ DateUtil.format(DateUtil.date(),"yyyy-MM-dd-hhMM-sss") +".xlsx");
        serverResponse.getHeaders().add("Accept-Ranges", "bytes");
        DefaultDataBuffer dataBuffer = new DefaultDataBufferFactory().allocateBuffer();
        OutputStream outputStream = dataBuffer.asOutputStream();
        Response downResponse = openFeignClient.downloadExcel(name);

        IOUtils.copy(downResponse.body().asInputStream(),outputStream);
        Flux<DataBuffer> dataBufferFlux = Flux.create((FluxSink<DataBuffer> emitter) -> {
            emitter.next(dataBuffer);
            emitter.complete();
        });
        return serverResponse.writeWith(dataBufferFlux);*//*
    }*/

    @GetMapping("/download")
    public Mono<Void> download(ServerHttpResponse serverResponse) {
        serverResponse.getHeaders().set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                        "filename=webflux-"+ DateUtil.format(DateUtil.date(),"yyyy-MM-dd-hhMM-sss") +".xlsx");
        serverResponse.getHeaders().add("Accept-Ranges", "bytes");
        DefaultDataBuffer dataBuffer = new DefaultDataBufferFactory().allocateBuffer();
        OutputStream outputStream = dataBuffer.asOutputStream();
        ExcelWriter writer = ExcelUtil.getBigWriter();
        writer.writeRow(List.of("1", "2", "3"));
        writer.flush(outputStream);
        Flux<DataBuffer> dataBufferFlux = Flux.create((FluxSink<DataBuffer> emitter) -> {
            emitter.next(dataBuffer);
            emitter.complete();
        });
        return serverResponse.writeWith(dataBufferFlux);
    }

}
