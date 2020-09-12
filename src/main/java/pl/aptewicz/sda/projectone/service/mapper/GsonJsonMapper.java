package pl.aptewicz.sda.projectone.service.mapper;

import com.google.gson.Gson;
import pl.aptewicz.sda.projectone.dto.IssPassTimesDto;
import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

public class GsonJsonMapper implements JsonMapper {
    private final Gson gson = new Gson();

    @Override
    public PeopleInSpaceDto mapPeopleInSpaceFromJson(String json) {
        return gson.fromJson(json, PeopleInSpaceDto.class);
    }

    @Override
    public IssPositionDto mapIssPositionDtoFromJson(String json) {
        // ODO: implement mapping from jso to dot using Gson lib
        try{
            return gson.fromJson(json,IssPositionDto.class);
        }catch (Exception e){
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public IssPassTimesDto mapIssPassTimesDtoFromJson(String json) {
        try{
            return gson.fromJson(json,IssPassTimesDto.class);
        }catch (Exception e){
            throw new UnsupportedOperationException();
        }
    }
}
