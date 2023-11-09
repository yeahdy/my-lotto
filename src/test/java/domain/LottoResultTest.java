package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import constants.LottoWinningPrize;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void init(){
        lottoResult = new LottoResult();
    }

    @Test
    @DisplayName("3등 1장 + 4등 2장 당첨 = 총 당첨금액 105,000원")
    void createTotalProfitTest(){
        //given
        lottoResult.setLottoWinning(3,false);
        lottoResult.setLottoWinning(4,false);
        lottoResult.setLottoWinning(4,false);

        //when
        BigDecimal totalProfit = lottoResult.createTotalProfit(lottoResult);

        //then
        assertThat(totalProfit.toString()).isEqualTo("105000");
    }


    @DisplayName("배열에 담긴 값은 로또 당첨 금액과 같다")
    @ParameterizedTest
    @ValueSource(strings = {"5000","50000","1500000","30000000","2000000000"})
    void getBigDecimalLottoPrizesTest(String prize) {
        // given
        BigDecimal[] lottoPrizes = Arrays.stream(LottoWinningPrize.values())
                .map(winningPrize -> new BigDecimal(winningPrize.getPrize()))
                .toArray(BigDecimal[]::new);

        // when
        boolean isEquals= false;
        for (BigDecimal lottoPrize : lottoPrizes) {
            isEquals = lottoPrize.toString().equals(prize);
            if (isEquals) break;
        }

        //then
        assertThat(isEquals).isTrue();
    }

}
