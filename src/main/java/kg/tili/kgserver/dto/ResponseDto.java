package kg.tili.kgserver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Markitanov Vadim
 * @since 06.04.2023
 */
@Data
@SuppressWarnings("unchecked")
@Builder(builderMethodName = "internalBuilder")
public class ResponseDto<D> {
    @NonNull
    private Boolean status;
    private String message;
    private D data;

    public static <D>ResponseDtoBuilder<D> success() {
        return (ResponseDtoBuilder<D>) internalBuilder().status(true);
    }

    public static <D>ResponseDtoBuilder<D> failure(String reason) {
        return (ResponseDtoBuilder<D>) internalBuilder().status(false).message(reason);
    }
}
