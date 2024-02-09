# Swallow Core - RISC-V I Base Extension

## Introduction
Swallow Core is an implementation of the RISC-V I Base Extension, a simple 32-bit RISC-V processor core. This repository contains the source code for Swallow Core and instructions on how to clone the repository, build the project using SBT (Simple Build Tool), and simulate the core using specific parameters.

## Getting Started

### Clone the Repository
To clone the Swallow Core repository, use the following command:
```bash
git clone https://github.com/your-username/swallow-core.git
cd swallow-core
```

### Build the Project with SBT
After cloning the repository, navigate to the project directory and run the following commands to build the project using SBT:
```bash
sbt
```

### Simulate Swallow Core
To simulate Swallow Core, use the following command after building the project with SBT:
```bash
sbt "testOnly riscv.top -- -DwriteVcd=1"
```
This command runs the test for the Swallow Core's RISC-V implementation and generates a VCD (Value Change Dump) file for further analysis.


### RV Core Tests
Swallow Core is tested using the RV Core Tests from the following repository: ```masfiyan/RV-Core-Tests```. Make sure to clone this repository as well and follow its instructions for running the tests.
