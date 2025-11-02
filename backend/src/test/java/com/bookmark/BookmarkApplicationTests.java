package com.bookmark;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@AnalyzeClasses(packages = "com.bookmark", importOptions = {ImportOption.DoNotIncludeTests.class})
@SpringBootTest
public class BookmarkApplicationTests {
  @ArchTest
  public static final ArchRule domainShouldNotUseFrameworkAnnotations =
      classes()
          .that()
          .resideInAPackage("..domain..")
          .should()
          .notBeAnnotatedWith("org.springframework.stereotype.Service")
          .andShould()
          .notBeAnnotatedWith("org.springframework.stereotype.Repository")
          .andShould()
          .notBeAnnotatedWith("org.springframework.transaction.annotation.Transactional")
          .andShould()
          .notBeAnnotatedWith("javax.persistence..")
          .andShould()
          .notBeAnnotatedWith("jakarta.persistence..");

  @ArchTest
  public static final ArchRule repositoryImplementationsMustBeInInfrastructure =
      classes()
          .that()
          .haveNameMatching(".*RepositoryAdapter")
          .or()
          .haveNameMatching(".*Entity")
          .should()
          .resideInAPackage("..infrastructure.persistence..");

  @ArchTest
  public static final ArchRule controllersMustResideInInterfaces =
      classes()
          .that()
          .haveNameMatching(".*Controller")
          .should()
          .resideInAPackage("..interfaces..")
          .andShould()
          .beAnnotatedWith("org.springframework.web.bind.annotation.RestController")
          .as("Controllers should be placed in the interfaces layer and use the appropriate Spring "
              + "annotation.");

  @ArchTest
  public static final ArchRule modulesShouldNotHaveCyclicDependencies =
      slices()
          .matching("com.bookmark.(*)..")
          .should()
          .beFreeOfCycles()
          .as("All top-level logical modules should be free of dependency cycles.");

  @Test
  public void contextLoads() {}
}
