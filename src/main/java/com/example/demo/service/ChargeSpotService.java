package com.example.demo.service;

import com.example.demo.dto.request.ChargeSpotRequestDTO;
import com.example.demo.dto.response.ChargeSpotMarkerResponsDTO;
import com.example.demo.entity.ChargeSpot;
import com.example.demo.repository.ChargeSpotRepository;
import com.example.demo.repository.ChargeSpotRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChargeSpotService {
    
    private final ChargeSpotRepository chargeSpotRepository;

    private final ChargeSpotRepositoryImpl chargeSpotRepositoryImpl;

    
    public List<ChargeSpotMarkerResponsDTO> getMarker(double lat, double lng, int zoom) {
        log.info("getMarker 동작!");
        List<ChargeSpot> chargeSpotList = chargeSpotRepository.findAll();

        // 마커 메서드
        final List<ChargeSpotMarkerResponsDTO> spotList = getChargeSpotMarkerResponsDTOS(lat, lng, zoom, chargeSpotList);

//                log.info(spotList.toString());
        
        return spotList;
    }



    public List<ChargeSpotMarkerResponsDTO> findSearch(ChargeSpotRequestDTO requestDTO) {

        final List<ChargeSpot> list = chargeSpotRepositoryImpl.findAll(requestDTO);
        double lat = Double.parseDouble(requestDTO.getLat());
        double lng = Double.parseDouble(requestDTO.getLng());
        int zoom = Integer.parseInt(requestDTO.getZoom());

        final List<ChargeSpotMarkerResponsDTO> spotList = getChargeSpotMarkerResponsDTOS(lat, lng, zoom, list);

        log.info(spotList.toString());

        return spotList;

    }

    @NotNull
    private static List<ChargeSpotMarkerResponsDTO> getChargeSpotMarkerResponsDTOS(double lat, double lng, int zoom, List<ChargeSpot> chargeSpotList) {
        List<ChargeSpotMarkerResponsDTO> spotList = new ArrayList<>();
        chargeSpotList.forEach((chargeSpot -> {
            String latLng = chargeSpot.getLatLng();
            String[] split = latLng.split(",");

            double yEpsilon = 0.02;
            double xEpsilon = 0.03;

            switch (zoom) {
                case 15, 16, 17, 18:
                    xEpsilon = 0.02;
                    yEpsilon = 0.01;
                    break;
                case 19, 20, 21:
                    xEpsilon = 0.007;
                    yEpsilon = 0.005;
                    break;
            }


            if (
                    Math.abs(Double.parseDouble(split[0]) - lat) < yEpsilon &&
                            Math.abs(Double.parseDouble(split[1]) - lng) < xEpsilon
            ) {
                ChargeSpotMarkerResponsDTO dto = ChargeSpotMarkerResponsDTO.builder()
                        .addr(chargeSpot.getAddr())
                        .statNm(chargeSpot.getStatNm())
                        .facilityBig(chargeSpot.getFacilityBig())
                        .facilitySmall(chargeSpot.getFacilitySmall())
                        .statId(chargeSpot.getStatId())
                        .limitYn(chargeSpot.getLimitYn())
                        .lat(split[0])
                        .lng(split[1])
                        .build();

                spotList.add(dto);
            }
        }));
        return spotList;
    }

}
