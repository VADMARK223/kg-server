package kg.tili.kgserver.web;

import kg.tili.kgserver.dto.DicDto;
import kg.tili.kgserver.dto.TypeDto;
import kg.tili.kgserver.dto.WordDto;
import kg.tili.kgserver.entity.Type;
import kg.tili.kgserver.entity.Word;
import kg.tili.kgserver.repository.TypeRepo;
import kg.tili.kgserver.repository.WordRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@CrossOrigin
@RestController
@AllArgsConstructor
public class WordController {
    private final WordRepo wordRepo;
    private final TypeRepo typeRepo;

    @RequestMapping(value = "/dic_get", method = RequestMethod.GET)
    public DicDto getAllDic() {
        DicDto dicDto = new DicDto();

        for (Type type : typeRepo.findAll()) {
            dicDto.types.add(new TypeDto(type.id, type.label));
        }

        for (Word word : wordRepo.findAll()) {
            dicDto.words.add(new WordDto(word.id.toString(), word.type.id, word.ru, word.kg));
        }
        return dicDto;
    }

    @RequestMapping(value = "/add_word", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addWord(@RequestBody WordDto dto) {
        System.out.println("New word: " + dto);
        Type type = typeRepo.getReferenceById(dto.type);
        System.out.println("Type: " + type);
        Word word = new Word();
        word.ru = dto.ru;
        word.kg = dto.kg;
        word.type = type;
        wordRepo.save(word);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/delete_word", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteWord(@RequestBody String id) {
        long wordId = Long.parseLong(id);
        System.out.println("Try delete word: " + wordId);
        if (wordRepo.existsById(wordId)) {
            wordRepo.deleteById(wordId);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
