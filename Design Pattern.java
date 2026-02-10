/* Offer Engine (Design Pattern)
Supported Offers
50% off on 3rd ticket
20% off on afternoon shows (12–4 PM)
Pattern Used
Chain of Responsibility */

public interface Offer {
    BigDecimal apply(OfferContext context);
}

public class AfternoonShowOffer implements Offer {
    public BigDecimal apply(OfferContext ctx) {
        if (ctx.isAfternoonShow()) {
            return ctx.getTotal()
                .multiply(BigDecimal.valueOf(0.8));
        }
        return ctx.getTotal();
    }
}
