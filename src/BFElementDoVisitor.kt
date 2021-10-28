interface Node {
    fun accept(visitor: Visitor)
}

interface Visitor {
    fun visit(left: Left)
    fun visit(right: Right)
    fun visit(increment: Increment)
    fun visit(decrement: Decrement)
    fun visit(output: Output)
    fun visit(input: Input)
    fun visit(loop: Loop)
    fun visit(program: Program)
}

class Left : Node {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class Input : Node {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}
class Output : Node {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}
class Right : Node {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class Increment : Node {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class Decrement : Node {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}
abstract class Sequence() : Node{
    val element: MutableList<Node> = mutableListOf<Node>()
    fun add(node: Node){
        element.add(node)
    }
}
class Program() : Sequence() {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

}
class Loop() : Sequence() {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

}
class InterpreterVisitor : Visitor {
    var byteArray = Array<Byte>(30000){0}
    var ptr = 0
    override fun visit(left: Left) {ptr-- }

    override fun visit(right: Right) {ptr++}

    override fun visit(increment: Increment) {byteArray[ptr]++}

    override fun visit(decrement: Decrement) {byteArray[ptr]--}

    override fun visit(output: Output) {
        print(byteArray[ptr].toInt().toChar())
    }

    override fun visit(input: Input) {}

    override fun visit(loop: Loop) {
        val byte: Byte = 0
        while(byteArray[ptr] != byte){
            for(node in loop.element)
                node.accept(this)
        }

    }

    override fun visit(program: Program) {
        for(node in program.element)
            node.accept(this)
    }
}

fun doParse(buf: StringBuffer, seq: Sequence): Sequence {
    // Store a single character
    var c: Char
    // As long as the string buffer isn't empty
    while (buf.length > 0) {
        // consume one character from the start of the buffer
        c = buf[0]
        buf.deleteCharAt(0)
        when (c) {
            '<' -> seq.add(Left())
            '>' -> seq.add(Right())
            '+' -> seq.add(Increment())
            '-' -> seq.add(Decrement())
            '.' -> seq.add(Output())
            ',' -> seq.add(Input())
            '[' -> seq.add(doParse(buf, Loop()))
            ']' -> return seq
        }
    }
    return seq
}
fun main(args: Array<String>) {
    // Parsing string and create a tree
    val str =
        "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
    // BrainF__k version hello word sent to buf
    // doParse will create a tree with program() as a root (program is a root node)
    val hello: Node = doParse(StringBuffer(str), Program())
    //hello.accept(new PrintVisitor());
    println()
    // Print hello world from interpreter
    hello.accept(InterpreterVisitor())
}

//    fun visit(left: Left)
//    fun visit(right: Right)
//    fun visit(increment: Increment)
//    fun visit(decrement: Decrement)
//    fun visit(output: Output)
//    fun visit(input: Input)
// doparse, print out each instruction.
// increment ptr 30 byte
// left ptr--
// right ptr++
// increment array[ptr]++
// decrement array[ptr]--
// output print.toInt.toChar


