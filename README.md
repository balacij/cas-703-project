# Validated General-Purposes Calculator

A project created for CAS 703, by Hassan Zaker Zavardehi and Jason Balaci.

## Objective

Build a language for generating validated calculators with test/assurance cases.

### Why

Common tooling in industry build their various “calculators” in an ad-hoc
fashion, without any sort of formal verification. In our experience, companies
rely on unspecialized calculators, such as Excel. Through generative techniques,
we hope to improve reliability and robustness of the used calculators by forcing
users and calculator developers alike to build the calculators using well-formed
descriptions of their required calculators. By forcing users and developers to
encode their calculation problems in a coherent language, we are able to
automatically analyze their solutions for reliability. Furthermore, through
generative techniques, the software becomes more maintainable, and traceable to
its origins.

## How

We intend to use the Epsilon ecosystem (including XText, EOL, EVL, EGX, EMF) to
build a general compiler and interpreter of our language. We will also use
Gradle for our build system.
