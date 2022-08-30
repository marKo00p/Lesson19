package ua.levelup.matrixAnnotation;


import ua.levelup.randomGenerators.FloatGenerator;
import ua.levelup.randomGenerators.IntegerGenerator;
import ua.levelup.randomGenerators.NumberGenerator;

public enum GeneratorTypes {
    INTEGER{
        @Override
        public NumberGenerator<? extends Number> getGenerator() {
            return new IntegerGenerator();
        }
    },
    FLOAT {
        @Override
        public NumberGenerator<? extends Number> getGenerator() {
            return new FloatGenerator();
        }
    };


    public abstract NumberGenerator<? extends Number> getGenerator();
}
