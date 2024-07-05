package sys.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDto {
    private Long id;
    private String description;
    private String title;
    private boolean completed;
}
