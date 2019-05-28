package tacos.ingredientclient.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tacos.ingredientclient.Ingredient;

import java.util.Collections;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<IngredientClient> {
    @Override
    public IngredientClient create(Throwable cause) {
        return new IngredientClient() {

            @Override
            public Ingredient getIngredient(String id) {
                return new Ingredient(null, "fallback1", Ingredient.Type.CHEESE);
            }

            @Override
            public Iterable<Ingredient> getAllIngredients() {
                return Collections.singletonList(new Ingredient(null, "fallback1", Ingredient.Type.CHEESE));
            }
        };
    }
}
