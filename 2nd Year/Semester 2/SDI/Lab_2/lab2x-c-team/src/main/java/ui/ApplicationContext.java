package ui;

import ui.commands.BaseCommand;

import java.util.*;

/**
 * Represents the shared data of the application.
 * Contains all the services used in the application.
 */
public class ApplicationContext {
    private final Set<Object> services;
    private final Map<String, Object> shared;

    public ApplicationContext() {
        this.services = new HashSet<>();
        this.shared = new HashMap<>();
    }

    public <T> void addService(T service) {
        this.services.add(service);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getService(Class<T> serviceClass){
        return Optional.ofNullable(serviceClass)
                .map(serviceClassNonNull -> (T) this.services.stream()
                        .filter(serviceClass::isInstance)
                        .findAny()
                        .orElse(null)
                );
    }

    public <T> void setShared(String key, T sharedObject) {
        this.shared.put(key, sharedObject);
    }

    public Optional<Object> getShared(String key){
        return Optional.ofNullable(this.shared.get(key));
    }
}
