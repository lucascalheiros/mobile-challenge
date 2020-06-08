## Comentários

* Listar filmes em cartaz incluindo o seu nome, poster, gênero e data de lançamento. Essa listagem deve implementar o conceito de infinite loading;

* Selecionar um filme e ver seus detalhes (nome, poster, gênero, overview, data de lançamento e idioma de origem);

* Buscar por filmes pelo título, possibilitando também sua seleção para visualização de detalhes. (Opcional);

* Salvar informações dos filmes visualizados em banco local para consultas futuras, evitando assim acessos desnecessários a API. (Opcional).

Todas as funcionalidades acima foram implementadas na aplicativo, no entanto não foram produzidos testes.


## Instruções de Build

-

## Bibliotecas Utilizadas

### Android Standard

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"
    implementation "androidx.appcompat:appcompat:1.1.0"
    implementation "androidx.core:core-ktx:1.2.0"

#### Views [Constraintlayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) [Recyclerview](https://developer.android.com/jetpack/androidx/releases/recyclerview) 

    implementation "androidx.constraintlayout:constraintlayout:1.1.3"
    implementation "androidx.recyclerview:recyclerview:1.1.0"

#### [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)

    implementation "androidx.navigation:navigation-fragment-ktx:2.0.0"
    implementation "androidx.navigation:navigation-ui-ktx:2.0.0"

### [RxJava2](https://github.com/ReactiveX/RxJava/tree/2.x)


    implementation "io.reactivex.rxjava2:rxjava:2.1.2"
    implementation "io.reactivex.rxjava2:rxandroid:2.0.1"

### [Retrofit2](https://square.github.io/retrofit/)

    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-moshi:2.9.0"
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.9.0"

### [Moshi](https://github.com/square/moshi)

    implementation "com.squareup.moshi:moshi:1.9.2"
    implementation "com.squareup.moshi:moshi-kotlin:1.9.2"

### [Glide](https://github.com/bumptech/glide)

    implementation "com.github.bumptech.glide:glide:4.11.0"
