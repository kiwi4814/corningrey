package easypoi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gas {
    private long dateL;
    private Object date;
    private Object hydGas;
    private Object natGas;
}
