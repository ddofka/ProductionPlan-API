package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateVideoRequest;
import org.codeacademy.productionplanapi.dto.get.GetVideoResponse;
import org.codeacademy.productionplanapi.entity.Video;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    GetVideoResponse videoToDto(Video video);

    List<GetVideoResponse> videoListToDto(List<Video> videos);

    default Page<GetVideoResponse> videoPageToDto(Page<Video> videos){
        return videos.map(this::videoToDto);
    }

    Video dtoToVideo(CreateVideoRequest request);

}
