from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class FindTitle(_message.Message):
    __slots__ = ["title"]
    TITLE_FIELD_NUMBER: _ClassVar[int]
    title: str
    def __init__(self, title: _Optional[str] = ...) -> None: ...

class FindCategory(_message.Message):
    __slots__ = ["category"]
    CATEGORY_FIELD_NUMBER: _ClassVar[int]
    category: str
    def __init__(self, category: _Optional[str] = ...) -> None: ...

class FindActor(_message.Message):
    __slots__ = ["actor"]
    ACTOR_FIELD_NUMBER: _ClassVar[int]
    actor: str
    def __init__(self, actor: _Optional[str] = ...) -> None: ...

class Movie(_message.Message):
    __slots__ = ["plot", "genres", "runtime", "cast", "num_mflix_comments", "title", "fullplot", "countries", "released", "directors", "rated", "lastupdated", "year", "type"]
    PLOT_FIELD_NUMBER: _ClassVar[int]
    GENRES_FIELD_NUMBER: _ClassVar[int]
    RUNTIME_FIELD_NUMBER: _ClassVar[int]
    CAST_FIELD_NUMBER: _ClassVar[int]
    NUM_MFLIX_COMMENTS_FIELD_NUMBER: _ClassVar[int]
    TITLE_FIELD_NUMBER: _ClassVar[int]
    FULLPLOT_FIELD_NUMBER: _ClassVar[int]
    COUNTRIES_FIELD_NUMBER: _ClassVar[int]
    RELEASED_FIELD_NUMBER: _ClassVar[int]
    DIRECTORS_FIELD_NUMBER: _ClassVar[int]
    RATED_FIELD_NUMBER: _ClassVar[int]
    LASTUPDATED_FIELD_NUMBER: _ClassVar[int]
    YEAR_FIELD_NUMBER: _ClassVar[int]
    TYPE_FIELD_NUMBER: _ClassVar[int]
    plot: str
    genres: _containers.RepeatedScalarFieldContainer[str]
    runtime: int
    cast: _containers.RepeatedScalarFieldContainer[str]
    num_mflix_comments: int
    title: str
    fullplot: str
    countries: _containers.RepeatedScalarFieldContainer[str]
    released: str
    directors: _containers.RepeatedScalarFieldContainer[str]
    rated: str
    lastupdated: str
    year: int
    type: str
    def __init__(self, plot: _Optional[str] = ..., genres: _Optional[_Iterable[str]] = ..., runtime: _Optional[int] = ..., cast: _Optional[_Iterable[str]] = ..., num_mflix_comments: _Optional[int] = ..., title: _Optional[str] = ..., fullplot: _Optional[str] = ..., countries: _Optional[_Iterable[str]] = ..., released: _Optional[str] = ..., directors: _Optional[_Iterable[str]] = ..., rated: _Optional[str] = ..., lastupdated: _Optional[str] = ..., year: _Optional[int] = ..., type: _Optional[str] = ...) -> None: ...

class FindResponse(_message.Message):
    __slots__ = ["movies"]
    MOVIES_FIELD_NUMBER: _ClassVar[int]
    movies: _containers.RepeatedCompositeFieldContainer[Movie]
    def __init__(self, movies: _Optional[_Iterable[_Union[Movie, _Mapping]]] = ...) -> None: ...

class Create(_message.Message):
    __slots__ = ["title", "plot", "fullplot"]
    TITLE_FIELD_NUMBER: _ClassVar[int]
    PLOT_FIELD_NUMBER: _ClassVar[int]
    FULLPLOT_FIELD_NUMBER: _ClassVar[int]
    title: str
    plot: str
    fullplot: str
    def __init__(self, title: _Optional[str] = ..., plot: _Optional[str] = ..., fullplot: _Optional[str] = ...) -> None: ...

class Update(_message.Message):
    __slots__ = ["title", "movies"]
    TITLE_FIELD_NUMBER: _ClassVar[int]
    MOVIES_FIELD_NUMBER: _ClassVar[int]
    title: str
    movies: Movie
    def __init__(self, title: _Optional[str] = ..., movies: _Optional[_Union[Movie, _Mapping]] = ...) -> None: ...

class Delete(_message.Message):
    __slots__ = ["title"]
    TITLE_FIELD_NUMBER: _ClassVar[int]
    title: str
    def __init__(self, title: _Optional[str] = ...) -> None: ...

class Response(_message.Message):
    __slots__ = ["msg"]
    MSG_FIELD_NUMBER: _ClassVar[int]
    msg: str
    def __init__(self, msg: _Optional[str] = ...) -> None: ...
