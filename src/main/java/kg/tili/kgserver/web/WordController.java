package kg.tili.kgserver.web;

import kg.tili.kgserver.dto.*;
import kg.tili.kgserver.entity.Tag;
import kg.tili.kgserver.entity.Type;
import kg.tili.kgserver.entity.Word;
import kg.tili.kgserver.repository.TagRepo;
import kg.tili.kgserver.repository.TypeRepo;
import kg.tili.kgserver.repository.WordRepo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@RestController
@AllArgsConstructor
public class WordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);
    private final WordRepo wordRepo;
    private final TypeRepo typeRepo;
    private final TagRepo tagRepo;

    @RequestMapping(value = "/get_dic", method = RequestMethod.GET)
    public ResponseEntity<DicDto> getAllDic() {
        DicDto dicDto = new DicDto();

        dicDto.getTypes().addAll(
                typeRepo.findAll()
                        .stream()
                        .map(type -> TypeDto
                                .builder()
                                .value(type.getId())
                                .label(type.getLabel())
                                .build()
                        )
                        .collect(Collectors.toList())
        );
        dicDto.getWords().addAll(
                wordRepo.findAll()
                        .stream()
                        .map(word -> WordDto
                                .builder()
                                .id(word.id())
                                .type(word.type().getId())
                                .ru(word.ru())
                                .kg(word.kg())
                                .tags(word.tags())
                                .build()
                        )
                        .collect(Collectors.toList())
        );
        dicDto.getTags().addAll(
                tagRepo.findAll()
                        .stream()
                        .map(tag -> TagDto
                                .builder()
                                .value(tag.getValue())
                                .label(tag.getLabel())
                                .color(tag.getColor())
                                .build()
                        )
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(dicDto);
    }

    @RequestMapping(value = "/save_word", method = RequestMethod.POST)
    public ResponseEntity<Boolean> saveWord(@RequestBody WordDto dto) {
        Type type = typeRepo.getReferenceById(dto.getType());
        Word word = new Word();
        if (dto.getId() != null) {
            word.id(dto.getId());
        }
        word.ru(dto.getRu());
        word.kg(dto.getKg());
        word.type(type);
        dto.getTags().forEach(tag -> {
            Tag newTag = tagRepo.getReferenceById(tag.getValue());
            word.tags().add(newTag);
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
}
