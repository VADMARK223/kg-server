package kg.tili.kgserver.web;

import kg.tili.kgserver.dto.ResponseDto;
import kg.tili.kgserver.dto.TagDto;
import kg.tili.kgserver.entity.Tag;
import kg.tili.kgserver.repository.TagRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 21.04.2023
 */
@RestController
@AllArgsConstructor
public class TagController {
    private final TagRepo tagRepo;

    @RequestMapping(value = "/get_tags", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<List<TagDto>>> getAllTags() {
        List<TagDto> result = new ArrayList<>();

        for (Tag tag : tagRepo.findAll()) {
            result.add(new TagDto(tag.getValue(), tag.getLabel(), tag.getColor()));
        }

        return ResponseEntity.ok(ResponseDto.<List<TagDto>>success().data(result).build());
    }

    @RequestMapping(value = "/save_tag", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<List<TagDto>>> saveTag(@RequestBody TagDto dto) {
        System.out.println("New tag: " + dto);
        Tag tag = new Tag();
//        if (dto.value != null) {
//            tag.setValue(dto.value);
//        }
        tag.setLabel(dto.getLabel());
        tagRepo.save(tag);
        return getAllTags();
    }
}
