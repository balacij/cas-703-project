/*
 * generated by Xtext 2.30.0
 */
package org.xtext.example.mydsl.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.mwe.ResourceDescriptionsProvider;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import calculator.AssignmentStep;
import calculator.CalculationStep;
import calculator.Calculator;
import calculator.Symbol;
import calculator.SymbolDeclaration;
import calculator.TestCase;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class MyDslValidator extends AbstractMyDslValidator {
	
	public static final String INVALID_NAME = "invalidName";

	@Check
	public void checkVariableUniqueName(Calculator calculator) {
		List<SymbolDeclaration> symbols = calculator.getSymbolDeclarations();
		Set<String> symbolNames = new HashSet<>();
		for (SymbolDeclaration sym : symbols) {
			String name = sym.getSymbol().getName();
			if(symbolNames.contains(name)) {
				error("This variable is already defined!", calculator.eContainingFeature());
			}
			
		}
	}
	
	
	@Check
	public void checkOutputsHaveAssignments(Calculator calculator) {
		List<SymbolDeclaration> symbols = calculator.getSymbolDeclarations();
		Set<String> symbolNames = new HashSet<>();
		Set<String> temp = new HashSet<>();
		Set<String> allVariables = new HashSet<>();
		for (SymbolDeclaration sym : symbols) {
			String name = sym.getSymbol().getName();
			allVariables.add(name);
			if(sym.getKind().getName() == "OUTPUT" || sym.getKind().getName() == "INTERMEDIATE") {
				symbolNames.add(name);
				temp.add(name);
			}
			
		}
		List<CalculationStep> steps = calculator.getSteps();
		
		for(CalculationStep step : steps) {
			if (step instanceof AssignmentStep) {
				AssignmentStep x = (AssignmentStep) step;
				if (symbolNames.contains(x.getSymbol().getName())) {
					temp.remove(x.getSymbol().getName());
				} else if (!allVariables.contains(x.getSymbol().getName())){
					error("This variable is not defined!", calculator.eContainingFeature());
				}
			}
		}
		
		if (temp.size() > 0) {
			error("All Output and Intermediate varaibles should be assigned at least once!", calculator.eContainingFeature());
		}
	}
	
	
	@Check
	public void checkInputAssignedInTestCases(Calculator calculator) {
		List<TestCase> testCases= calculator.getTestCases();
		List<AssignmentStep> steps= new ArrayList<>();
		for (TestCase test : testCases) {
			steps.addAll(test.getAssignments());
		}
		
		
		List<SymbolDeclaration> symbols = calculator.getSymbolDeclarations();
		Set<String> symbolNames = new HashSet<>();
		Set<String> temp = new HashSet<>();
		Set<String> allVariables = new HashSet<>();
		for (SymbolDeclaration sym : symbols) {
			String name = sym.getSymbol().getName();
			if(sym.getKind().getName() == "INPUT") {
				symbolNames.add(name);
				temp.add(name);
			} else {
				allVariables.add(name);
			}
			
		}
		
		
		for (AssignmentStep step: steps) {
			AssignmentStep x = step;
			if (symbolNames.contains(x.getSymbol().getName())) {
				temp.remove(x.getSymbol().getName());
			} else if (allVariables.contains(x.getSymbol().getName())){
				error("Output and Intermediate variables should not be assigned in a value for test cases!", calculator.eContainingFeature());
			} else {
				error("This variable is not defined!", calculator.eContainingFeature());
			}
			
		}
			
		
		if (temp.size() > 0) {
			error("All Input variables should be assigned at least once in testcase section!", calculator.eContainingFeature());
		}
	}
	
	
	
	
}

	

