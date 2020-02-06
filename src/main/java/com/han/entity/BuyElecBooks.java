package com.han.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BuyElecBooks
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 21:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyElecBooks {
    private Long id;
    private Long user_id;
    private Long elec_book_id;
}
