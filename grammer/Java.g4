/** Java 1.6 grammar (ANTLR v4). Derived from

    http://docs.oracle.com/javase/specs/jls/se7/jls7.pdf

    and JavaParser.g from ANTLR v3
 */
grammar Java;

@header{
import java.util.ArrayList;
}

@lexer::members {
  protected boolean enumIsKeyword = true;
  protected boolean assertIsKeyword = true;
}
@parser::members{

    ArrayList<String> list=new ArrayList<String>();
    int temp = 0;
    int label = 0;
    Stack stack = new Stack();
    java.util.List<Boolean> exists=new ArrayList<Boolean>();

    String newLabel(){
        return "l"+ ++label;
    }
     String newTemp(){
            return "t"+ ++temp;
        }
    void print(String s){
        System.out.println(s);
    }
}
// starting point for parsing a java file
compilationUnit
    :   packageDeclaration? importDeclaration* typeDeclaration*
        EOF
    ;

packageDeclaration
    :   'package' qualifiedName ';'
    ;

importDeclaration
    :   'import' 'static'? qualifiedName ('.' '*')? ';'
    ;

typeDeclaration
    :   classOrInterfaceModifier*
        (   classDeclaration
        |   interfaceDeclaration
        |   enumDeclaration
        )
    |   ';'
    ;

classDeclaration
    :   'class' Identifier typeParameters? ('extends' type)?
        ('implements' typeList)?
        classBody
    ;

enumDeclaration
    :   ENUM Identifier ('implements' typeList)? enumBody
    ;

interfaceDeclaration
    :   normalInterfaceDeclaration
    |   annotationTypeDeclaration
    ;

classOrInterfaceModifier
    :   annotation   // class or interface
    |   'public'     // class or interface
    |   'protected'  // class or interface
    |   'private'    // class or interface
    |   'abstract'   // class or interface
    |   'static'     // class or interface
    |   'final'      // class only -- does not apply to interfaces
    |   'strictfp'   // class or interface
    ;

modifiers
    :   modifier*
    ;

typeParameters
    :   '<' typeParameter (',' typeParameter)* '>'
    ;

typeParameter
    :   Identifier ('extends' typeBound)?
    ;

typeBound
    :   type ('&' type)*
    ;

enumBody
    :   '{' enumConstants? ','? enumBodyDeclarations? '}'
    ;

enumConstants
    :   enumConstant (',' enumConstant)*
    ;

enumConstant
    :   annotations? Identifier arguments? classBody?
    ;

enumBodyDeclarations
    :   ';' (classBodyDeclaration)*
    ;

normalInterfaceDeclaration
    :   'interface' Identifier typeParameters? ('extends' typeList)? interfaceBody
    ;

typeList
    :   type (',' type)*
    ;

classBody
    :   '{' classBodyDeclaration* '}'
    ;

interfaceBody
    :   '{' interfaceBodyDeclaration* '}'
    ;

classBodyDeclaration
    :   ';'
    |   'static'? block
    |   modifiers member
    ;

member
    :   genericMethodDeclaration
    |   methodDeclaration
    |   fieldDeclaration
    |   constructorDeclaration
    |   interfaceDeclaration
    |   classDeclaration
    ;

methodDeclaration
    :   type Identifier formalParameters ('[' ']')* methodDeclarationRest
    |   'void' Identifier formalParameters methodDeclarationRest
    ;

methodDeclarationRest
    :   ('throws' qualifiedNameList)?
        (   methodBody
        |   ';'
        )
    ;

genericMethodDeclaration
    :   typeParameters methodDeclaration
    ;

fieldDeclaration
    :   type variableDeclarators ';'
    ;

constructorDeclaration
    :   typeParameters? Identifier formalParameters
        ('throws' qualifiedNameList)? constructorBody
    ;

interfaceBodyDeclaration
    :   modifiers interfaceMemberDecl
    |   ';'
    ;

interfaceMemberDecl
    :   interfaceMethodOrFieldDecl
    |   interfaceGenericMethodDecl
    |   'void' Identifier voidInterfaceMethodDeclaratorRest
    |   interfaceDeclaration
    |   classDeclaration
    ;

interfaceMethodOrFieldDecl
    :   type Identifier interfaceMethodOrFieldRest
    ;

interfaceMethodOrFieldRest
    :   constantDeclaratorsRest ';'
    |   interfaceMethodDeclaratorRest
    ;

voidMethodDeclaratorRest
    :   formalParameters ('throws' qualifiedNameList)?
        (   methodBody
        |   ';'
        )
    ;

interfaceMethodDeclaratorRest
    :   formalParameters ('[' ']')* ('throws' qualifiedNameList)? ';'
    ;

interfaceGenericMethodDecl
    :   typeParameters (type | 'void') Identifier
        interfaceMethodDeclaratorRest
    ;

voidInterfaceMethodDeclaratorRest
    :   formalParameters ('throws' qualifiedNameList)? ';'
    ;

constantDeclarator
    :   Identifier constantDeclaratorRest
    ;

variableDeclarators
    :   variableDeclarator (',' variableDeclarator)*
    ;

variableDeclarator
    :   variableDeclaratorId ('=' variableInitializer)?
    ;

constantDeclaratorsRest
    :   constantDeclaratorRest (',' constantDeclarator)*
    ;

constantDeclaratorRest
    :   ('[' ']')* '=' variableInitializer
    ;

variableDeclaratorId
    :   Identifier ('[' ']')*
    ;

variableInitializer
    :   arrayInitializer
    |   expression
    ;

arrayInitializer
    :   '{' (variableInitializer (',' variableInitializer)* (',')? )? '}'
    ;

modifier
    :   annotation
    |   'public'
    |   'protected'
    |   'private'
    |   'static'
    |   'abstract'
    |   'final'
    |   'native'
    |   'synchronized'
    |   'transient'
    |   'volatile'
    |   'strictfp'
    ;

packageOrTypeName
    :   qualifiedName
    ;

enumConstantName
    :   Identifier
    ;

typeName
    :   qualifiedName
    ;

type:   classOrInterfaceType ('[' ']')*
    |   primitiveType ('[' ']')*
    ;

classOrInterfaceType
    :   Identifier typeArguments? ('.' Identifier typeArguments? )*
    ;

primitiveType
    :   'boolean'
    |   'char'
    |   'byte'
    |   'short'
    |   'int'
    |   'long'
    |   'float'
    |   'double'
    ;

variableModifier
    :   'final'
    |   annotation
    ;

typeArguments
    :   '<' typeArgument (',' typeArgument)* '>'
    ;

typeArgument
    :   type
    |   '?' (('extends' | 'super') type)?
    ;

qualifiedNameList
    :   qualifiedName (',' qualifiedName)*
    ;

formalParameters
    :   '(' formalParameterDecls? ')'
    ;

formalParameterDecls
    :   variableModifiers type formalParameterDeclsRest
    ;

formalParameterDeclsRest
    :   variableDeclaratorId (',' formalParameterDecls)?
    |   '...' variableDeclaratorId
    ;

methodBody
    :   block
    ;

constructorBody
    :   '{' explicitConstructorInvocation? blockStatement* '}'
    ;

explicitConstructorInvocation
    :   nonWildcardTypeArguments? ('this' | 'super') arguments ';'
    |   primary '.' nonWildcardTypeArguments? 'super' arguments ';'
    ;

qualifiedName
    :   Identifier ('.' Identifier)*
    ;

literal
    :   integerLiteral
    |   FloatingPointLiteral
    |   CharacterLiteral
    |   StringLiteral
    |   booleanLiteral
    |   'null'
    ;

integerLiteral
    :   HexLiteral
    |   OctalLiteral
    |   DecimalLiteral
    ;

booleanLiteral
    :   'true'
    |   'false'
    ;

// ANNOTATIONS

annotations
    :   annotation+
    ;

annotation
    :   '@' annotationName ( '(' ( elementValuePairs | elementValue )? ')' )?
    ;

annotationName
    : Identifier ('.' Identifier)*
    ;

elementValuePairs
    :   elementValuePair (',' elementValuePair)*
    ;

elementValuePair
    :   Identifier '=' elementValue
    ;

elementValue
    :   expression
    |   annotation
    |   elementValueArrayInitializer
    ;

elementValueArrayInitializer
    :   '{' (elementValue (',' elementValue)*)? (',')? '}'
    ;

annotationTypeDeclaration
    :   '@' 'interface' Identifier annotationTypeBody
    ;

annotationTypeBody
    :   '{' (annotationTypeElementDeclaration)* '}'
    ;

annotationTypeElementDeclaration
    :   modifiers annotationTypeElementRest
    ;

annotationTypeElementRest
    :   type annotationMethodOrConstantRest ';'
    |   classDeclaration ';'?
    |   normalInterfaceDeclaration ';'?
    |   enumDeclaration ';'?
    |   annotationTypeDeclaration ';'?
    ;

annotationMethodOrConstantRest
    :   annotationMethodRest
    |   annotationConstantRest
    ;

annotationMethodRest
    :   Identifier '(' ')' defaultValue?
    ;

annotationConstantRest
    :   variableDeclarators
    ;

defaultValue
    :   'default' elementValue
    ;

// STATEMENTS / BLOCKS

block
    :   '{' blockStatement* '}'
    ;

blockStatement
    :   localVariableDeclarationStatement
    |   classDeclaration
    |   interfaceDeclaration
    |   statement
    ;

localVariableDeclarationStatement
    :    localVariableDeclaration ';'
    ;

localVariableDeclaration
    :   variableModifiers type variableDeclarators
    ;

variableModifiers
    :   variableModifier*
    ;

statement returns [String val] locals [String l1,String l2,boolean exi]
    : block
    |   ASSERT expression (':' expression)? ';'
    |   'if' parExpression {
            $exi = false;
            $l1 = newLabel();
            print("if NOT " + $parExpression.val +" goto "+$l1);
        } statement ('else' {
            $exi = true;
            $l2 = newLabel();
            print("goto "+$l2);
            print($l1+":");
        } statement {print($l2+":");})?{
            if(! $exi){
                {print($l1+":");}
            }
        }
    |   'for' '(' f = forControl ')' s = statement {
            if($f.l_start != null && $f.l_end != null){
                print("goto " + $f.l_start);
                print($f.l_end+":");
            }
        }
    |   'while'{
           $l1 = newLabel();
           $l2 = newLabel();
           print($l1+":");

       } parExpression{
           print("if NOT " + $parExpression.val +" goto "+$l2);
       } statement {
           print("goto "+$l1);
           print($l2+":");
       }
    |   'do' {
            $l1 = newLabel();
            $l2 = newLabel();
            print($l1+":");

    } statement 'while' parExpression {
         print("if NOT " + $parExpression.val +" goto "+$l2);
         print("goto "+$l1);
         print($l2+":");
     } ';'
    |   'try' block
        ( catches 'finally' block
        | catches
        | 'finally' block
        )
    |   'switch' parExpression switchBlock
    |   'synchronized' parExpression block
    |   'return' expression? ';'
    |   'throw' expression ';'
    |   'break' Identifier? ';'
    |   'continue' Identifier? ';'
    |   ';'
    |   statementExpression ';'
    |   Identifier ':' statement
    ;

catches
    :   catchClause (catchClause)*
    ;

catchClause
    :   'catch' '(' formalParameter ')' block
    ;

formalParameter
    :   variableModifiers type variableDeclaratorId
    ;

switchBlock
    :   '{' switchBlockStatementGroup* switchLabel* '}'
    ;

switchBlockStatementGroup
    :   switchLabel+ blockStatement*
    ;

switchLabel
    :   'case' constantExpression ':'
    |   'case' enumConstantName ':'
    |   'default' ':'
    ;

forControl returns [String val, String l_start,String l_end] locals [String l1,String l2,String t1, boolean exi]
    :   enhancedForControl
    |   {$exi = false; $l_start = $l_end = $l1 = newLabel(); }(forInit)? ';'{print($l1+":");} (e=expression{
            $exi = true;
            if($e.val != null){
                $l2 = newLabel();
                $l_end = $l2;
                $t1 = newTemp();
                print($t1 + " = "+$e.val);
                print("if NOT "+$t1+" goto "+ $l2);
            }
            })? {
                if(! $exi){
                    $l2 = newLabel();
                    $l_end = $l2;
                    print("if FALSE "+$t1+" goto "+ $l2);
                }
            }';' forUpdate?
    ;

forInit returns [String val]
    :   localVariableDeclaration
    |   expressionList
    ;

enhancedForControl
    :   variableModifiers type Identifier ':' expression
    ;

forUpdate returns [String val]
    :   expressionList {$val = $expressionList.val;}
    ;

// EXPRESSIONS

parExpression returns [String val]
    :   '(' expression ')' {$val = $expression.val;}
    ;

expressionList returns [String val]
    :   e1 = expression {$val = $e1.val;} (',' expression)*
    ;

statementExpression
    :   expression
    ;

constantExpression
    :   expression
    ;

expression returns [String val] locals [String op,String t,String l1,String l2]
    :   primary {$val = $primary.val;}
    |   expression '.' Identifier
    |   expression '.' 'this'
    |   expression '.' 'super' '(' expressionList? ')'
    |   expression '.' 'new' Identifier '(' expressionList? ')'
    |   expression '.' 'super' '.' Identifier arguments?
    |   expression '.' explicitGenericInvocation
    |   expression '[' expression ']'
    |   expression '(' expressionList? ')'
    |   e=expression ('++' {$op="++";}| '--'{$op="--";}){
          if($e.val != null){
               $t = newTemp();
               $val = $t;
              if($op == "++"){
                  print($val +" = "+ $e.val + " + " + 1);
                  print($e.val +" = "+ $val);
                }else if($op == "--"){
                  print($val +" = "+ $e.val + " - " + 1);
                  print($e.val +" = "+ $val);
                }
           }
      }
    |   ('+'{$op="+";}|'-'{$op="-";}|'++'{$op="++";}|'--'{$op="--";}) e=expression{
            if($e.val != null){
                 $t = newTemp();
                 $val = $t;
                 if($op == "+"){
                    $val = $e.val;
                 }else if($op == "-"){
                    print($val +" = "+ $e.val + " * -1");
                 }else if($op == "++"){
                    print($val +" = "+ $e.val + " + " + 1);
                    print($e.val +" = "+ $val);
                  }else if($op == "--"){
                    print($val +" = "+ $e.val + " - " + 1);
                    print($e.val +" = "+ $val);
                  }
             }
        }
    |   ('~'|'!') expression
    |   '(' type ')' expression
    |   'new' creator
    |   e1=expression ('*'{$op=" * ";}|'/'{$op=" / ";}|'%'{$op=" % ";}) e2=expression{

             if($e1.val != null && $e2.val != null){
                 $t = newTemp();
                 $val = $t;
                 print($val +" = "+$e1.val + $op + $e2.val);
             }else if($e1.val == null && $e2.val != null){
             }else if($e1.val != null && $e2.val == null){
             }else if($e1.val == null && $e2.val == null){
             }
       }
    |   e1=expression ('+'{$op=" + ";}|'-'{$op=" - ";}) e2=expression{
            if($e1.val != null && $e2.val != null){
                 $t = newTemp();
                 $val = $t;
                 print($t +" = "+$e1.val + $op + $e2.val);
             }else if($e1.val == null && $e2.val != null){
             }else if($e1.val != null && $e2.val == null){
             }else if($e1.val == null && $e2.val == null){
             }
        }
    |   e1=expression ('<' '<' {$op=" << ";}| '>' '>' '>' {$op=" >>> ";}| '>' '>' {$op=" >> ";}) e2=expression{
              if($e1.val != null && $e2.val != null){
                  $t = newTemp();
                  $val = $t;
                  print($val+" = "+$e1.val + $op + $e2.val);
              }

          }
    |   e1=expression ('<' '=' {$op=" <= ";}| '>' '=' {$op=" >= ";}| '>' {$op=" > ";}| '<' {$op=" < ";}) e2=expression{
            if($e1.val != null && $e2.val != null){
                $t = newTemp();
                $val = $t;
                print($val+" = "+$e1.val + $op + $e2.val);
            }

        }
    |   expression 'instanceof' type
    |   e1=expression ('==' {$op=" == ";}| '!=' {$op=" != ";}) e2=expression
    |   e1=expression '&' e1=expression {
            if($e1.val != null && $e2.val != null){
                $t = newTemp();
                $val = $t;
                print($t +" = "+$e1.val + " & " + $e2.val);
            }
        }
    |   expression '^' expression{
         if($e1.val != null && $e2.val != null){
             $t = newTemp();
             $val = $t;
             print($t +" = "+$e1.val + " ^ " + $e2.val);
         }
     }
    |   expression '|' expression{
          if($e1.val != null && $e2.val != null){
              $t = newTemp();
              $val = $t;
              print($t +" = "+$e1.val + " | " + $e2.val);
          }
      }
    |   expression '&&' expression{
        if($e1.val != null && $e2.val != null){
            $t = newTemp();
            $val = $t;
            print($t +" = "+$e1.val + " && " + $e2.val);
        }
     }
    |   expression '||' expression{
          if($e1.val != null && $e2.val != null){
              $t = newTemp();
              $val = $t;
              print($t +" = "+$e1.val + " || " + $e2.val);
          }
      }
    |   e1=expression {
          $t = newTemp();
          if($e1.val != null){
              $l1 = newLabel();
              print("if NOT " + $e1.val +" goto "+ $l1);
          }
    } '?' e2=expression {
            if($e1.val != null && $e2.val != null ){
                $l2 = newLabel();
                print($t + " = " +$e2.val);
                print("goto "+$l2);
                print($l1+":");
                $val = $t;
            }

    }':' e3=expression {
        if($e1.val != null && $e2.val != null && $e3.val != null){
            print($t + " = " +$e3.val);
            $val = $t;
            print($l2+":");
        }

        }
    |  <assoc=right> e1=expression
        ('^=' {$op="^=";}
        |'+=' {$op="+=";}
        |'-=' {$op="-=";}
        |'*=' {$op="*=";}
        |'/=' {$op="/=";}
        |'&=' {$op="&=";}
        |'|=' {$op="|=";}
        |'=' {$op="=";}
        |'>' '>' '=' {$op=">>=";}
        |'>' '>' '>' '=' {$op=">>>=";}
        |'<' '<' '=' {$op="<<=";}
        |'%=' {$op="%=";}
        )
        e2=expression{
            if($e1.val != null && $e2.val != null){
                $t = newTemp();
                $val = $t;
                if($op == "^="){
                    print($t +" = "+$e1.val + " ^ " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "+="){
                    print($t +" = "+$e1.val + " + " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "-="){
                    print($t +" = "+$e1.val + " - " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "*="){
                    print($t +" = "+$e1.val + " * " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "/="){
                    print($t +" = "+$e1.val + " / " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "&="){
                    print($t +" = "+$e1.val + " & " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "="){
                    print($e1.val +" = "+ $e2.val);
                    $val = $e1.val;
                }else if($op == ">>="){
                    print($t +" = "+$e1.val + " >> " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == ">>>="){
                    print($t +" = "+$e1.val + " >>> " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "<<="){
                    print($t +" = "+$e1.val + " << " + $e2.val);
                    print($e1.val +" = "+ $t);
                }else if($op == "%="){
                    print($t +" = "+$e1.val + " % " + $e2.val);
                    print($e1.val +" = "+ $t);
                }
            }
        }
    ;

primary returns [String val]
    :   '(' expression ')'{$val = $expression.val;}
    |   'this'
    |   'super'
    |   literal {$val = $literal.text;}
    |   Identifier {$val = $Identifier.text;}
    |   type '.' 'class'
    |   'void' '.' 'class'
    ;

creator
    :   nonWildcardTypeArguments createdName classCreatorRest
    |   createdName (arrayCreatorRest | classCreatorRest)
    ;

createdName
    :   classOrInterfaceType
    |   primitiveType
    ;

innerCreator
    :   nonWildcardTypeArguments? Identifier classCreatorRest
    ;

explicitGenericInvocation
    :   nonWildcardTypeArguments Identifier arguments
    ;

arrayCreatorRest
    :   '['
        (   ']' ('[' ']')* arrayInitializer
        |   expression ']' ('[' expression ']')* ('[' ']')*
        )
    ;

classCreatorRest
    :   arguments classBody?
    ;

nonWildcardTypeArguments
    :   '<' typeList '>'
    ;

arguments
    :   '(' expressionList? ')'
    ;

// LEXER

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ FloatTypeSuffix
    |   ('0x' | '0X') (HexDigit )*
        ('.' (HexDigit)*)?
        ( 'p' | 'P' )
        ( '+' | '-' )?
        ( '0' .. '9' )+
        FloatTypeSuffix?
    ;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

CharacterLiteral
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

StringLiteral
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')
    |   UnicodeEscape
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

ENUM:   'enum' {if (!enumIsKeyword) setType(Identifier);}
    ;

ASSERT
    :   'assert' {if (!assertIsKeyword) setType(Identifier);}
    ;

Identifier
    :   Letter (Letter|JavaIDDigit)*
    ;

/**I found this char range in JavaCC's grammar, but Letter and Digit overlap.
   Still works, but...
 */
fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

fragment
JavaIDDigit
    :  '\u0030'..'\u0039' |
       '\u0660'..'\u0669' |
       '\u06f0'..'\u06f9' |
       '\u0966'..'\u096f' |
       '\u09e6'..'\u09ef' |
       '\u0a66'..'\u0a6f' |
       '\u0ae6'..'\u0aef' |
       '\u0b66'..'\u0b6f' |
       '\u0be7'..'\u0bef' |
       '\u0c66'..'\u0c6f' |
       '\u0ce6'..'\u0cef' |
       '\u0d66'..'\u0d6f' |
       '\u0e50'..'\u0e59' |
       '\u0ed0'..'\u0ed9' |
       '\u1040'..'\u1049'
   ;

COMMENT
    :   '/*' .*? '*/'    -> channel(HIDDEN) // match anything between /* and */
    ;
WS  :   [ \r\t\u000C\n]+ -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
    ;

