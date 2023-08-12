package kg.tili.kgserver.web;

import kg.tili.kgserver.dto.*;
import kg.tili.kgserver.entity.Tag;
import kg.tili.kgserver.entity.Type;
import kg.tili.kgserver.entity.Word;
import kg.tili.kgserver.repository.TagRepo;
import kg.tili.kgserver.repository.TypeRepo;
import kg.tili.kgserver.repository.WordRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@RestController
@AllArgsConstructor
public class WordController {
    private final WordRepo wordRepo;
    private final TypeRepo typeRepo;
    private final TagRepo tagRepo;

    @RequestMapping(value = "/get_dic", method = RequestMethod.GET)
    public ResponseEntity<DicDto> getAllDic() {
        DicDto dicDto = new DicDto();

        for (Type type : typeRepo.findAll()) {
            dicDto.getTypes().add(new TypeDto(type.id, type.label));
        }

        for (Word word : wordRepo.findAll()) {
            dicDto.getWords().add(new WordDto(word.id, word.type.id, word.ru, word.kg, word.getTags()));
        }

        for (Tag tag : tagRepo.findAll()) {
            dicDto.getTags().add(new TagDto(tag.getValue(), tag.getLabel(), tag.getColor()));
        }

        return ResponseEntity.ok(dicDto);
    }

    @RequestMapping(value = "/save_word", method = RequestMethod.POST)
    public ResponseEntity<Boolean> saveWord(@RequestBody WordDto dto) {
        System.out.println("New word: " + dto);
        Type type = typeRepo.getReferenceById(dto.type);
        Word word = new Word();
        if (dto.id != null) {
            word.id = dto.id;
        }
        word.ru = dto.ru;
        word.kg = dto.kg;
        word.type = type;
        dto.tags.forEach(tag -> {
            Tag newTag = tagRepo.getReferenceById(tag.getValue());
            word.getTags().add(newTag);
        });

        wordRepo.save(word);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/delete_word", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDto<Object>> deleteWord(@RequestBody String id) {
        long wordId = Long.parseLong(id);
        System.out.println("Try delete word: " + wordId);

        if (!wordRepo.existsById(wordId)) {
            return ResponseEntity.ok(ResponseDto.failure("Слово не найдено.").build());
        }

        wordRepo.deleteById(wordId);
        return ResponseEntity.ok(ResponseDto.success().build());
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<TempDto>> success() {
        return ResponseEntity.ok(ResponseDto.<TempDto>success().data(new TempDto()).build());
    }

    @RequestMapping(value = "/failure", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<TempDto>> failure() {
        return ResponseEntity.ok(ResponseDto.<TempDto>failure("Reason error").data(new TempDto()).build());
    }
}
