# Problems

1. 12421 (Jiandan) Mua(I) - Lexical Analyzer: https://uva.onlinejudge.org/external/124/12421.pdf
2. 12422 (Kengdie) Mua(II) - Expression Evaluation: https://uva.onlinejudge.org/external/124/12422.pdf
3. 12423 (Last) Mua (III) - Full Interpreter: https://uva.onlinejudge.org/external/124/12423.pdf

# The Complete Syntax of Mua
```
    chunk ::= block

    block ::= {stat} [retstat]

    stat ::= ‘;’ |
        var ‘=’ exp |
        functioncall |
        break |
        do block end |
        while exp do block end |
        repeat block until exp |
        if exp then block {elseif exp then block} [else block] end |
        for Name ‘=’ exp ‘,’ exp [‘,’ exp] do block end |
        for Name in exp do block end |
        function funcname funcbody |
        local Name [‘=’ exp]

    retstat ::= return [exp] [‘;’]

    funcname ::= Name {'.' Name}

    var ::= Name | prefixexp ‘[’ exp ‘]’ | prefixexp ‘.’ Name

    namelist ::= Name {‘,’ Name}

    explist ::= exp {‘,’ exp}

    exp ::= Number | String | nil | false | true | ‘{’ ‘}’ | prefixexp |
        exp binop exp | unop exp

    prefixexp ::= var | functioncall | ‘(’ exp ‘)’

    functioncall ::= var ‘(’ explist ‘)’

    funcbody ::= ‘(’ [namelist] ‘)’ block end

    binop ::= ‘+’ | ‘-’ | ‘*’ | ‘/’ | ‘^’ | ‘%’ | ‘..’ |
        ‘<’ | ‘<=’ | ‘>’ | ‘>=’ | ‘==’ | ‘~=’ |
        and | or

    unop ::= ‘-’ | not | ‘#’
```

# Environment Setup
1. Build package: ```mvn clean package```
2. Run main program: ```java -cp target/mua-1.0-SNAPSHOT.jar com.menggen.mua.Main``` (dummy for now)

# References
1. Lua 5.3 Reference Manual: http://www.lua.org/manual/5.3/manual.html
2. V8 JavaScript engine: https://v8.dev/
3. Luaparse: https://oxyc.github.io/luaparse/