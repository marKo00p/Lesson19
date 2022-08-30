package ua.levelup.randomGenerators;

public class FloatGenerator extends AbstractGenerator<Float>{

    @Override
    public Float nextRand() {

        return random.nextFloat();
    }

    @Override
    public Class<Float> getType() {
        return Float.class;
    }
}
