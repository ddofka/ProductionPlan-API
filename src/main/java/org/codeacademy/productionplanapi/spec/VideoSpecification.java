package org.codeacademy.productionplanapi.spec;

import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.springframework.data.jpa.domain.Specification;

public class VideoSpecification {

    public static Specification<Video> hasStatus(PostStatus status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
                //condition ? valueIfTrue : valueIfFalse
                //this syntax is called ternary operator(triju daliu operatorius), an if-else statement
    }

    public static Specification<Video> hasStage(ProductionStage stage) {
        return (root, query, criteriaBuilder) ->
                stage == null ? null : criteriaBuilder.equal(root.get("stage"), stage);
    }

    public static Specification<Video> hasPriority(Priority priority) {
        return (root, query, criteriaBuilder) ->
                priority == null ? null : criteriaBuilder.equal(root.get("priority"), priority);
    }

    public static Specification<Video> hasDirectorName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("director").get("name"), "%" + name + "%");
    }

    public static Specification<Video> hasEditorName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("editor").get("name"), "%" + name + "%");
    }

    public static Specification<Video> hasCompilationName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("compilationName"), "%" + name + "%");
    }
}
