# 🎮 Jogo da Forca - Console (Java)

Aplicação simples em **Java** que simula o clássico **Jogo da Forca** no console.  
Este projeto foi desenvolvido como parte do **Desafio 2** do **Bootcamp NTT DATA - Java e IA para Iniciantes** na [DIO](https://web.dio.me/home).  

---

## 📌 Funcionalidades

- Sorteio de uma palavra secreta.  
- Entrada de letras pelo jogador.  
- Verificação automática se a letra já foi escolhida.  
- Exibição do estado atual da palavra e do boneco da forca.  
- Fim de jogo em caso de vitória ou derrota.  

---

## 🛠 Tecnologias utilizadas

- **Java 17**  
- **Gradle** (para gerenciamento do projeto, mas não obrigatório para execução)  

---

## 🚀 Como executar no console

Clone o repositório:

```bash
git clone https://github.com/robsonosbor/jogo-forca.git
cd jogo-forca/app/src/main/java
```

Compile os arquivos:

```bash
javac br/com/dio/**/*.java
```

Execute o jogo:

```bash
java br.com.dio.Main
```

---

## 🖥 Exemplo de execução

### 🔹 Início do jogo

```text
Bem-vindo ao Jogo da Forca!

Palavra: _ _ _ _ _ _
Tentativas restantes: 6
Digite uma letra:
```

---

### 🔹 Jogando

```text
Digite uma letra: a

Palavra: _ a _ a _
Tentativas restantes: 6
Letras já escolhidas: a
```

---

### 🔹 Vitória 🎉

```text
Digite uma letra: o

Palavra: c a s a
Tentativas restantes: 5
Letras já escolhidas: a, c, s, o

Parabéns! Você venceu o jogo! 🎉
```

---

### 🔹 Derrota 💀

```text
Digite uma letra: z

  +---+
  |   |
  O   |
 /|\\  |
 / \\  |
      ===

Palavra secreta era: casa
Você perdeu! 💀
```

---

## 👨‍💻 Autor

Projeto desenvolvido por **Robson Alves Batista**  
🔗 [GitHub - robsonosbor](https://github.com/robsonosbor)

---
