package gamesoft.slots.domain.model.slot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import gamesoft.slots.domain.model.slot.Coin;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

public class CoinUTest {
	
	private static final CurrencyUnit USD = CurrencyUnit.USD;
	private static final Coin $10 = Coin.parse("USD 10.00");
	private static final Coin $5 = Coin.parse("USD 5");
	private static final Coin $2 = Coin.parse("USD 2.00");
	private static final Coin £5 = Coin.parse("GBP 5.00");;

	@Test
	public void testParseBigDecimal(){
		Coin coin = Coin.parse(new BigDecimal("10.00"), USD);
		assertThat(coin, equalTo($10));
	}

	@Test
	public void testCompareTo() {
		assertThat($10, equalTo($10));
		assertThat($10, greaterThan($5));
		assertThat($10, greaterThan($2));
		assertThat($5, greaterThan($2));
		assertThat($2, lessThan($5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCompareToIncompatibleCoins(){		
		$5.compareTo(£5);
	}
	
}
