package com.droolsprogrammatic.utilidades;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.util.Arrays;
import java.util.List;

public class KsessionBuilder {

    private static final List<String> FILES;

    static {
        FILES = Arrays.asList(
          "puntos.drl"
        );
    }

    public KieSession createSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = createFileSystem(kieServices);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();

        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            System.err.println(kieBuilder.getResults());
            throw new IllegalArgumentException("Error al cargar la base de conocimiento.");
        }

        KieContainer kieContainer = kieServices
            .newKieContainer(kieServices.getRepository().getDefaultReleaseId());

        return kieContainer.newKieSession();
    }

    private KieFileSystem createFileSystem(KieServices kieServices){
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        for (String path : FILES) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(path, this.getClass()));
        }

        return kieFileSystem;
    }

}
