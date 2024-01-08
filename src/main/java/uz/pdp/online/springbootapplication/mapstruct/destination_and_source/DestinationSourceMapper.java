package uz.pdp.online.springbootapplication.mapstruct.destination_and_source;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DestinationSourceMapper {
    Destination toDestination(Source source);

    Source toSource(Destination destination);

    List<Destination> toDestinationList(List<Source> sources);

    List<Source> toSourceList(List<Destination> destinations);
}
