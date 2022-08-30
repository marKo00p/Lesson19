package ua.levelup.matrixAnnotation;

import ua.levelup.randomGenerators.NumberGenerator;
import ua.levelup.randomGenerators.TwoDArray;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestUser  {


    @MatrixProperty(generator = @GeneratorProperty(GeneratorTypes.INTEGER), rows = 3, column = 3)
    @MatrixProperty(generator = @GeneratorProperty(GeneratorTypes.FLOAT), rows = 5, column = 5)
    public <T extends Number> void testMatrix1(TwoDArray<T> matrix) {
        matrix.print();
    }

    public static void main(String[] args) {
        TestUser testUser = new TestUser();
        testMatrix(testUser);
    }

    private static void testMatrix(Object object) {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            MatrixProperties matrixProperties = method.getAnnotation(MatrixProperties.class);
            if (matrixProperties == null) {
                continue;
            }
            long start = System.nanoTime();
            ExecutorService exec = Executors.newFixedThreadPool(matrixProperties.value().length);


            for (MatrixProperty matrixProperty : matrixProperties.value()) {
                exec.execute(() -> {
                    Runnable matrixPropertiesRun = () -> {

                        try {

                            NumberGenerator<?> generator = matrixProperty.generator().value().getGenerator();

                            method.invoke(object, new TwoDArray<>(generator, matrixProperty.rows(), matrixProperty.column()));

                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }

                    };

                    matrixPropertiesRun.run();
                });
                System.out.println("Total time = " + ((System.nanoTime() - start) / 1_000_000d));
            }
            exec.shutdown();
        }
    }
    }

