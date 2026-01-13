package felipemcassiano.fainansu.controllers;

import felipemcassiano.fainansu.dtos.EntryDTO;
import felipemcassiano.fainansu.dtos.RegisterEntryDTO;
import felipemcassiano.fainansu.services.EntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entries")
public class EntryController {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }



    @PostMapping("")
    public ResponseEntity<Void> post(@RequestBody RegisterEntryDTO req) {

        System.out.println(req);

        entryService.registerEntry(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<EntryDTO>> getAllEntries() {

        List<EntryDTO> all = entryService.findAllEntries(true);
        return   ResponseEntity.ok(all);

    }

}
