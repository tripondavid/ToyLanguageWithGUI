package view;

import model.expressions.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;

public class Examples {
    StatementInterface s1 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface s2 = new AssignStatement("v", new ValueExpression(new IntValue(2)));
    StatementInterface s3 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s4 = new CompoundStatement(s2, s3);
    StatementInterface s5 = new CompoundStatement(s1, s4);



    StatementInterface s6 = new VariableDeclarationStatement("a", new IntType());
    StatementInterface s7 = new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)),
            new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+'));

    StatementInterface s8 = new VariableDeclarationStatement("b", new IntType());
    ExpressionInterface e1 = new ArithmeticExpression(new ValueExpression(new IntValue(4)), new ValueExpression(new IntValue(2)), '/');
    ExpressionInterface e2 = new ArithmeticExpression(new VariableExpression("a"), e1, '-');
    ExpressionInterface e3 = new ArithmeticExpression(e2, new ValueExpression(new IntValue(7)), '+');
    StatementInterface s9 = new AssignStatement("b", e3);
    StatementInterface s10 = new PrintStatement(new VariableExpression("b"));
    StatementInterface s11 = new CompoundStatement(s9, s10);
    StatementInterface s12 = new CompoundStatement(s8, s11);
    StatementInterface s13 = new CompoundStatement(s7, s12);
    StatementInterface s14 = new CompoundStatement(s6, s13);



    StatementInterface s15 = new VariableDeclarationStatement("a", new BoolType());
    StatementInterface s16 = new AssignStatement("a", new ValueExpression(new BoolValue(false)));
    StatementInterface s17 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface s18 = new AssignStatement("v", new ValueExpression(new IntValue(2)));
    StatementInterface s19 = new AssignStatement("v", new ValueExpression(new IntValue(3)));
    StatementInterface s20 = new IfStatement(new VariableExpression("a"), s18, s19);
    StatementInterface s21 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s22 = new CompoundStatement(s20, s21);
    StatementInterface s25 = new CompoundStatement(s17, s22);
    StatementInterface s26 = new CompoundStatement(s16, s25);
    StatementInterface s27 = new CompoundStatement(s15, s26);


    StatementInterface s28 = new VariableDeclarationStatement("varf", new StringType());
    StatementInterface s29 = new AssignStatement("varf", new ValueExpression(new StringValue("test.in")));
    StatementInterface s30 = new OpenReadFileStatement(new VariableExpression("varf"));
    StatementInterface s31 = new VariableDeclarationStatement("varc", new IntType());
    StatementInterface s32 = new ReadFileStatement(new VariableExpression("varf"), "varc");
    StatementInterface s33 = new PrintStatement(new VariableExpression("varc"));
    StatementInterface s34 = new ReadFileStatement(new VariableExpression("varf"), "varc");
    StatementInterface s35 = new PrintStatement(new VariableExpression("varc"));
    StatementInterface s36 = new CloseReadFileStatement(new VariableExpression("varf"));
    StatementInterface s37 = new CompoundStatement(s35, s36);
    StatementInterface s38 = new CompoundStatement(s34, s37);
    StatementInterface s39 = new CompoundStatement(s33, s38);
    StatementInterface s40 = new CompoundStatement(s32, s39);
    StatementInterface s41 = new CompoundStatement(s31, s40);
    StatementInterface s42 = new CompoundStatement(s30, s41);
    StatementInterface s43 = new CompoundStatement(s29, s42);
    StatementInterface s44 = new CompoundStatement(s28, s43);



    StatementInterface s45 = new VariableDeclarationStatement("v", new RefType(new IntType()));
    StatementInterface s46 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
    StatementInterface s47 = new VariableDeclarationStatement("a", new RefType(new RefType(new IntType())));
    StatementInterface s48 = new HeapAllocationStatement("a", new VariableExpression("v"));
    StatementInterface s49 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s50 = new PrintStatement(new VariableExpression("a"));
    StatementInterface s51 = new CompoundStatement(s49, s50);
    StatementInterface s52 = new CompoundStatement(s48, s51);
    StatementInterface s53 = new CompoundStatement(s47, s52);
    StatementInterface s54 = new CompoundStatement(s46, s53);
    StatementInterface s55 = new CompoundStatement(s45, s54);


    StatementInterface s56 = new VariableDeclarationStatement("v", new RefType(new IntType()));
    StatementInterface s57 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
    StatementInterface s58 = new VariableDeclarationStatement("a", new RefType(new RefType(new IntType())));
    StatementInterface s59 = new HeapAllocationStatement("a", new VariableExpression("v"));
    StatementInterface s60 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(30)));
    StatementInterface s61 = new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))));
    StatementInterface s62 = new CompoundStatement(s60, s61);
    StatementInterface s63 = new CompoundStatement(s59, s62);
    StatementInterface s64 = new CompoundStatement(s58, s63);
    StatementInterface s65 = new CompoundStatement(s57, s64);
    StatementInterface s66 = new CompoundStatement(s56, s65);


    StatementInterface s67 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface s68 = new AssignStatement("v", new ValueExpression(new IntValue(4)));
    ExpressionInterface e20 = new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">");
    StatementInterface s69 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s70 = new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '-'));
    StatementInterface s71 = new CompoundStatement(s69, s70);
    StatementInterface s72 = new WhileStatement(e20, s71);
    StatementInterface s73 = new CompoundStatement(s68, s72);
    StatementInterface s74 = new CompoundStatement(s67, s73);


    StatementInterface s75 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface s76 = new VariableDeclarationStatement("a", new RefType(new IntType()));
    StatementInterface s77 = new AssignStatement("v", new ValueExpression(new IntValue(10)));
    StatementInterface s78 = new HeapAllocationStatement("a", new ValueExpression(new IntValue(22)));
    StatementInterface s79 = new HeapWritingStatement("a", new ValueExpression(new IntValue(30)));
    StatementInterface s80 = new AssignStatement("v", new ValueExpression(new IntValue(32)));
    StatementInterface s81 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s82 = new PrintStatement(new ReadHeapExpression(new VariableExpression("a")));
    StatementInterface s83 = new CompoundStatement(s81, s82);
    StatementInterface s84 = new CompoundStatement(s80, s83);
    StatementInterface s85 = new CompoundStatement(s79, s84);
    StatementInterface s86 = new ForkStatement(s85);
    StatementInterface s87 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s88 = new PrintStatement(new ReadHeapExpression(new VariableExpression("a")));
    StatementInterface s89 = new CompoundStatement(s87, s88);
    StatementInterface s90 = new CompoundStatement(s86, s89);
    StatementInterface s91 = new CompoundStatement(s78, s90);
    StatementInterface s92 = new CompoundStatement(s77, s91);
    StatementInterface s93 = new CompoundStatement(s76, s92);
    StatementInterface s94 = new CompoundStatement(s75, s93);

    // lock table example
    StatementInterface s200 = new VariableDeclarationStatement("v1", new RefType(new IntType()));
    StatementInterface s201 = new HeapAllocationStatement("v1", new ValueExpression(new IntValue(20)));
    StatementInterface s202 = new VariableDeclarationStatement("v2", new RefType(new IntType()));
    StatementInterface s203 = new HeapAllocationStatement("v2", new ValueExpression(new IntValue(30)));
    StatementInterface s204 = new NewLockStatement("x");
    StatementInterface s205 = new LockLockStatement("x");
    StatementInterface s206 = new HeapWritingStatement("v1",
            new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)), '-'));

    StatementInterface s207 = new UnlockLockStatement("x");
    StatementInterface s208 = new CompoundStatement(s206, s207);
    StatementInterface s209 = new CompoundStatement(s205, s208); // inside the second fork

    StatementInterface s210 = new ForkStatement(s209); // second fork
    StatementInterface s211 = new LockLockStatement("x");
    StatementInterface s212 = new HeapWritingStatement("v1",
            new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)), '+'));
    StatementInterface s213 = new UnlockLockStatement("x");
    StatementInterface s214 = new CompoundStatement(s212, s213);
    StatementInterface s215 = new CompoundStatement(s211, s214); // inside first fork but outside second fork

    StatementInterface s216 = new CompoundStatement(s210, s215);
    StatementInterface s217 = new ForkStatement(s216); // first fork


    StatementInterface s218 = new HeapWritingStatement("v2",
            new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(1)), '+'));
    StatementInterface s219 = new ForkStatement(s218); // 4th fork
    StatementInterface s220 = new HeapWritingStatement("v2",
            new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(1)), '+'));
    StatementInterface s221 = new UnlockLockStatement("x");
    StatementInterface s222 = new CompoundStatement(s220, s221);
    StatementInterface s223 = new CompoundStatement(s219, s222);
    StatementInterface s224 = new ForkStatement(s223); // 3rd fork

    StatementInterface s225 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v1")));
    StatementInterface s226 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v2")));
    StatementInterface s227 = new CompoundStatement(s225, s226);
    StatementInterface s228 = new CompoundStatement(s224, s227);
    StatementInterface s229 = new CompoundStatement(s217, s228);
    StatementInterface s230 = new CompoundStatement(s204, s229);
    StatementInterface s231 = new CompoundStatement(s203, s230);
    StatementInterface s232 = new CompoundStatement(s202, s231);
    StatementInterface s233 = new CompoundStatement(s201, s232);
    StatementInterface s234 = new CompoundStatement(s200, s233);
    StatementInterface s235 = new VariableDeclarationStatement("x", new IntType());
    StatementInterface s236 = new CompoundStatement(s235, s234);


    StatementInterface s300 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface s301 = new AssignStatement("v", new ValueExpression(new IntValue(20)));
    StatementInterface s303 = new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")),
            new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '+'))));
    StatementInterface s302 = new ForStatement(new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)),
            new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '+'),
            s303);

    StatementInterface s304 = new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(10)), '*'));
    StatementInterface s305 = new CompoundStatement(s302, s304);
    StatementInterface s306 = new CompoundStatement(s301, s305);
    StatementInterface s307 = new CompoundStatement(s300, s306);


    // repeat until
    StatementInterface s400 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface s401 = new VariableDeclarationStatement("x", new IntType());
    StatementInterface s402 = new VariableDeclarationStatement("y", new IntType());
    StatementInterface s403 = new AssignStatement("x", new ValueExpression(new IntValue(0)));
    StatementInterface s404 = new PrintStatement(new VariableExpression("v"));
    StatementInterface s405 = new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"),
            new ValueExpression(new IntValue(1)), '-'));
    StatementInterface s406 = new ForkStatement(new CompoundStatement(s404, s405));
    StatementInterface s407 = new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"),
            new ValueExpression(new IntValue(1)), '+'));
    StatementInterface s408 = new CompoundStatement(s406, s407);
    StatementInterface s409 = new RepeatUntilStatement(s408, new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(3)), "!="));
    StatementInterface s410 = new AssignStatement("x", new ValueExpression(new IntValue(1)));
    StatementInterface s411 = new AssignStatement("y", new ValueExpression(new IntValue(3)));
    StatementInterface s412 = new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(10)), '*'));
    StatementInterface s413 = new CompoundStatement(s411, s412);
    StatementInterface s414 = new CompoundStatement(s410, s413);
    StatementInterface s415 = new CompoundStatement(s409, s414);
    StatementInterface s416 = new CompoundStatement(s403, s415);
    StatementInterface s417 = new CompoundStatement(s402, s416);
    StatementInterface s418 = new CompoundStatement(s401, s417);
    StatementInterface s419 = new CompoundStatement(s400, s418);


    StatementInterface s500 = new VariableDeclarationStatement("v1", new RefType(new IntType()));
    StatementInterface s501 = new VariableDeclarationStatement("v2", new RefType(new IntType()));
    StatementInterface s502 = new VariableDeclarationStatement("v3", new RefType(new IntType()));
    StatementInterface s503 = new VariableDeclarationStatement("cnt", new IntType());
    StatementInterface s504 = new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2)));
    StatementInterface s505 = new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3)));
    StatementInterface s506 = new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4)));
    StatementInterface s507 = new NewBarrierStatement("cnt", new ReadHeapExpression(new VariableExpression("v2")));
    StatementInterface s508 = new AwaitBarrierStatement("cnt");
    StatementInterface s509 = new HeapWritingStatement("v1", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")),
            new ValueExpression(new IntValue(10)),
            '*'));
    StatementInterface s510 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v1")));
    StatementInterface s511 = new CompoundStatement(s509, s510);
    StatementInterface s512 = new CompoundStatement(s508, s511);
    StatementInterface s513 = new ForkStatement(s512);
    StatementInterface s514 = new AwaitBarrierStatement("cnt");
    StatementInterface s515 = new HeapWritingStatement("v2", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v2")),
            new ValueExpression(new IntValue(10)),
            '*'));
    StatementInterface s516 = new HeapWritingStatement("v2", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v2")),
            new ValueExpression(new IntValue(10)),
            '*'));
    StatementInterface s517 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v2")));
    StatementInterface s518 = new CompoundStatement(s516, s517);
    StatementInterface s519 = new CompoundStatement(s515, s518);
    StatementInterface s520 = new CompoundStatement(s514, s519);
    StatementInterface s521 = new ForkStatement(s520);
    StatementInterface s522 = new AwaitBarrierStatement("cnt");
    StatementInterface s523 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v3")));
    StatementInterface s524 = new CompoundStatement(s522, s523);
    StatementInterface s525 = new CompoundStatement(s521, s524);
    StatementInterface s526 = new CompoundStatement(s513, s525);
    StatementInterface s527 = new CompoundStatement(s507, s526);
    StatementInterface s528 = new CompoundStatement(s506, s527);
    StatementInterface s529 = new CompoundStatement(s505, s528);
    StatementInterface s530 = new CompoundStatement(s504, s529);
    StatementInterface s531 = new CompoundStatement(s503, s530);
    StatementInterface s532 = new CompoundStatement(s502, s531);
    StatementInterface s533 = new CompoundStatement(s501, s532);
    StatementInterface s534 = new CompoundStatement(s500, s533);


    StatementInterface s600 = new VariableDeclarationStatement("v1", new RefType(new IntType()));
    StatementInterface s601 = new VariableDeclarationStatement("v2", new RefType(new IntType()));
    StatementInterface s602 = new VariableDeclarationStatement("v3", new RefType(new IntType()));
    StatementInterface s603 = new VariableDeclarationStatement("cnt", new IntType());
    StatementInterface s604 = new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2)));
    StatementInterface s605 = new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3)));
    StatementInterface s606 = new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4)));
    StatementInterface s607 = new NewLatchStatement("cnt", new ReadHeapExpression(new VariableExpression("v2")));
    StatementInterface s608 = new ForkStatement(new HeapWritingStatement("v1", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")),
            new ValueExpression(new IntValue(10)),
            '*')));
    StatementInterface s609 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v1")));
    StatementInterface s610 = new CountDownLatchStatement("cnt");
    StatementInterface s611 = new ForkStatement(new HeapWritingStatement("v2", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v2")),
            new ValueExpression(new IntValue(10)),
            '*')));
    StatementInterface s612 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v2")));
    StatementInterface s613 = new CountDownLatchStatement("cnt");
    StatementInterface s614 = new ForkStatement(new HeapWritingStatement("v3", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v3")),
            new ValueExpression(new IntValue(10)),
            '*')));
    StatementInterface s615 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v3")));
    StatementInterface s616 = new CountDownLatchStatement("cnt");
    StatementInterface s617 = new AwaitLatchStatement("cnt");
    StatementInterface s618 = new PrintStatement(new ValueExpression(new IntValue(100)));
    StatementInterface s619 = new CountDownLatchStatement("cnt");
    StatementInterface s620 = new PrintStatement(new ValueExpression(new IntValue(100)));
    StatementInterface s621 = new CompoundStatement(s619, s620);
    StatementInterface s622 = new CompoundStatement(s618, s621);
    StatementInterface s623 = new CompoundStatement(s617, s622);
    StatementInterface s624 = new CompoundStatement(s616, s623);
    StatementInterface s625 = new CompoundStatement(s615, s624);
    StatementInterface s626 = new CompoundStatement(s614, s625);
    StatementInterface s627 = new CompoundStatement(s613, s626);
    StatementInterface s628 = new CompoundStatement(s612, s627);
    StatementInterface s629 = new CompoundStatement(s611, s628);
    StatementInterface s630 = new CompoundStatement(s610, s629);
    StatementInterface s631 = new CompoundStatement(s609, s630);
    StatementInterface s632 = new CompoundStatement(s608, s631);
    StatementInterface s633 = new CompoundStatement(s607, s632);
    StatementInterface s634 = new CompoundStatement(s606, s633);
    StatementInterface s635 = new CompoundStatement(s605, s634);
    StatementInterface s636 = new CompoundStatement(s604, s635);
    StatementInterface s637 = new CompoundStatement(s603, s636);
    StatementInterface s638 = new CompoundStatement(s602, s637);
    StatementInterface s639 = new CompoundStatement(s601, s638);
    StatementInterface s640 = new CompoundStatement(s600, s639);
    public StatementInterface[] exampleList() {
        return new StatementInterface[]{s5, s14, s27, s44, s55, s66, s74, s94, s236, s307, s419, s534, s640};
    }
}
