package de.fhdo.lemma.model_processing.code_generation.ethereum;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.ImportTargetElementType;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.SerializationCharacteristic;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.UtilKt;


public class EthereumUtil {

    public static void addConfigurationAnnotation(ClassOrInterfaceDeclaration clazz){
        UtilKt.addAndGetAnnotation(
                clazz,
                "Configuration",
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        UtilKt.addImport(
                clazz,
                "org.springframework.context.annotation.Configuration",
                ImportTargetElementType.ANNOTATION,
                SerializationCharacteristic.values(),
                false
        );
    }

    /** Add an private attribute,
     * whose value shall be injected by Spring at runtime via the @Value annotation from a property */
    public static FieldDeclaration addPrivatePropertyInjectionAttribute(String attributeName,
                                                            String attributeTypeName,
                                                            String propertyName,
                                                            ClassOrInterfaceDeclaration clazz){
        Modifier.Keyword[] keywords = new Modifier.Keyword[]{
                Modifier.Keyword.PRIVATE
        };
        FieldDeclaration attribute = UtilKt.addAttribute(
                clazz,
                attributeName,
                attributeTypeName,
                keywords,
                null);
        NormalAnnotationExpr annotationExpr = UtilKt.addAndGetAnnotation(
                attribute,
                "Value",
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        annotationExpr.addPair("value", "\"${"+propertyName+"}\"");
        UtilKt.addImport(attribute,"org.springframework.beans.factory.annotation.Value", ImportTargetElementType.ANNOTATION);
        return attribute;
    }



    /** Add an public attribute,
     * whose value shall be injected by Spring at runtime via the @Value annotation from a property */
    public static FieldDeclaration addPublicPropertyInjectionAttribute(String attributeName,
                                                           String attributeTypeName,
                                                           String propertyName,
                                                           ClassOrInterfaceDeclaration clazz){
        Modifier.Keyword[] keywords = new Modifier.Keyword[]{
                Modifier.Keyword.PUBLIC
        };
        FieldDeclaration attribute = UtilKt.addAttribute(
                clazz,
                attributeName,
                attributeTypeName,
                keywords,
                null);
        NormalAnnotationExpr annotationExpr = UtilKt.addAndGetAnnotation(
                attribute,
                "Value",
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        annotationExpr.addPair("value", "\"${"+propertyName+"}\"");
        UtilKt.addImport(attribute,"org.springframework.beans.factory.annotation.Value", ImportTargetElementType.ANNOTATION);
        return attribute;
    }



    /** Add a getter-function for an attribute */
    public static void addGetterMethod(String methodName,
                                       String returnType,
                                       String attributeName,
                                       ClassOrInterfaceDeclaration clazz){
        MethodDeclaration methodDeclaration = clazz.addMethod(methodName, Modifier.Keyword.PUBLIC);
        methodDeclaration.setType(returnType);
        UtilKt.setBody(
                methodDeclaration,
                "return this."+attributeName,
                "",
                "",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }



    /** Add a getter-function for an attribute */
    public static void addSetterMethod(String methodName,
                                       String attributeName,
                                       String attributeType,
                                       ClassOrInterfaceDeclaration clazz){
        MethodDeclaration methodDeclaration = clazz.addMethod(methodName, Modifier.Keyword.PUBLIC);
        NodeList<Parameter> nodeList = new NodeList<>();
        nodeList.add(new Parameter().setType(attributeType).setName(attributeName));
        methodDeclaration.setParameters(nodeList);
        UtilKt.setBody(
                methodDeclaration,
                "this." + attributeName + " = " + attributeName,
                "",
                "",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }
}
