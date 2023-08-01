# JavaFX-Brunata-Keys
Program for company use with using JavaFX

###       To use program it's neccassary to use Maven plugin: 

              <plugin>
                <!-- plugin to create JAR file maven-shade-plugin-->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <shadedArtifactAttached>true</shadedArtifactAttached>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>pl.wartego.javafxtest.Main</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>


###            and create additional Main class: 

            public class Main {
    public static void main(String[] args) {
        HelloApplication.main(args);
    }
}


#### to compile file to jar file it's neccassary in Maven:

1. compile
2. install

##### then open jar file. 

