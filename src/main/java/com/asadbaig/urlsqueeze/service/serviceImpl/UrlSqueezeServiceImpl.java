package com.asadbaig.urlsqueeze.service.serviceImpl;

import com.asadbaig.urlsqueeze.entity.UrlInfoEntity;
import com.asadbaig.urlsqueeze.exception.UrlSqueezeException;
import com.asadbaig.urlsqueeze.model.UrlSqueezeRequest;
import com.asadbaig.urlsqueeze.model.UrlSqueezeResponse;
import com.asadbaig.urlsqueeze.model.enums.ResponseCode;
import com.asadbaig.urlsqueeze.repository.UrlInfoRepository;
import com.asadbaig.urlsqueeze.service.UrlSqueezeService;
import com.asadbaig.urlsqueeze.util.UrlHashingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlSqueezeServiceImpl implements UrlSqueezeService {

    @Autowired
    UrlHashingUtil urlHashingUtil;

    @Autowired
    UrlInfoRepository urlInfoRepository;

    @Value("${url.squeeze.host}")
    private String urlSqueezeHost;

    @Value("${squeezed.url.id.size}")
    private int urlIdSize;


    @Override
    public ResponseEntity<UrlSqueezeResponse> squeezeUrl(UrlSqueezeRequest urlSqueezeRequest) {
        try {
            String originalUrl = urlSqueezeRequest.getUrl();
            String urlHash = urlHashingUtil.getUrlHash(originalUrl);
            UrlSqueezeResponse urlSqueezeResponse = buildUrlSqueezeResponse(originalUrl, urlHash);
            buildAndSaveUrlInfo(originalUrl, urlHash, urlSqueezeResponse.getSqueezedUrl());
            return new ResponseEntity<>(urlSqueezeResponse, HttpStatus.OK);
        } catch(Exception e) {
            throw new UrlSqueezeException(ResponseCode.US500, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteUrl(String squeezedUrl) {
        String urlIdToDelete = StringUtils.substring(squeezedUrl, squeezedUrl.length() - urlIdSize);;
        try{
            int urlsDeleted = urlInfoRepository.deleteByUrlId(urlIdToDelete);
            if(urlsDeleted == 0) {
                return new ResponseEntity<>("Url Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Url Deleted Successfully", HttpStatus.OK);
        } catch(Exception e) {
            throw new UrlSqueezeException(ResponseCode.UD500, e.getMessage());
        }
    }

    private UrlSqueezeResponse buildUrlSqueezeResponse(String originalUrl, String urlHash) {
        String squeezedUrl = StringUtils.join(urlSqueezeHost, urlHash);
        return UrlSqueezeResponse.builder()
                .responseCode(ResponseCode.US200.name())
                .responseDescription(ResponseCode.US200.getDescription())
                .originalUrl(originalUrl)
                .squeezedUrl(squeezedUrl)
                .build();
    }

    private void buildAndSaveUrlInfo(String originalUrl, String urlHash, String squeezedUrl) {
        UrlInfoEntity urlInfo = UrlInfoEntity.builder()
                .originalUrl(originalUrl)
                .squeezedUrl(squeezedUrl)
                .urlId(urlHash)
                .isModifiable("N")
                .build();
        urlInfoRepository.save(urlInfo);
    }

}