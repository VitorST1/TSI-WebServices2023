from lxml import etree
xml = etree.parse('biblioteca.xml')
root = xml.getroot()


def obterTotalLivros():
    total = 0

    for livro in root.find('livros'):
        total += int(livro.attrib['quantidade'])

    return total


def obterLivrosXml():
    return root.find('livros').findall('livro')


def obterTotalLivrosEmprestados():
    totalLivrosEmprestados = 0

    for livroXml in obterLivrosXml():
        totalLivrosEmprestados += int(livroXml.attrib['emprestados'])

    return totalLivrosEmprestados


def obterPorcentagemLivrosEmprestados():
    return "{:.2f}%".format((obterTotalLivrosEmprestados() / obterTotalLivros()) * 100)


def obterLivrosAutor(autor):
    total = 0

    for livroXml in obterLivrosXml():
        for a in livroXml.findall('autor'):
            if a.text == autor:
                total += 1

    return total


def obterLivroComMenosPaginas():
    livros = obterLivrosXml()
    paginas = livros[0].find('paginas').text
    livro = livros[0].find('titulo').text

    if len(livros) > 1:
        for livroXml in livros:
            if int(livroXml.find('paginas').text) < int(paginas):
                paginas = livroXml.find('paginas').text
                livro = livroXml.find('titulo').text

    return livro


def obterLivrosComMultiplosAutores():
    livros = []

    for livroXml in obterLivrosXml():
        if len(livroXml.findall('autor')) > 1:
            livros.append(livroXml.find('titulo').text)

    return ', '.join(livros)


print(
    f'a. Porcentagem de livros emprestados: {obterPorcentagemLivrosEmprestados()}')
print(f'b. Quantidade de títulos do Deitel: {obterLivrosAutor("Deitel")}')
print(f'c. Livro com menos páginas: {obterLivroComMenosPaginas()}')
print(f'd. Livros com mais de um autor: {obterLivrosComMultiplosAutores()}')
